package at.stefl.commons.lwxml.reader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PushbackReader;
import java.io.Reader;

import at.stefl.commons.io.ApplyFilterReader;
import at.stefl.commons.io.CharFilter;
import at.stefl.commons.io.CharStreamUtil;
import at.stefl.commons.io.FullyReader;
import at.stefl.commons.io.UntilCharReader;
import at.stefl.commons.io.UntilCharSequenceReader;
import at.stefl.commons.io.UntilFilterReader;
import at.stefl.commons.lwxml.LWXMLConstants;
import at.stefl.commons.lwxml.LWXMLEvent;
import at.stefl.commons.util.InaccessibleSectionException;

// TODO: improve code
// TODO: improve malformed xml handling
// TODO: use xml escaping decoder
public class LWXMLStreamReader extends LWXMLReader {

    private static final int PUSHBACK_BUFFER_SIZE = 1;

    private static final CharFilter WHITESPACE_FILTER = new CharFilter() {

        @Override
        public boolean accept(final char c) {
            return !LWXMLConstants.isWhitespace(c);
        }
    };

    private final CharFilter startElementFilter = new CharFilter() {

        @Override
        public boolean accept(final char c) {
            if (LWXMLConstants.isWhitespace(c)) {
                return false;
            }

            switch (c) {
                case '/':
                case '>':
                    try {
                        LWXMLStreamReader.this.in.unread(c);
                        return false;
                    } catch (final IOException e) {
                        throw new InaccessibleSectionException();
                    }
            }

            return true;
        }
    };

    private static final char[] CDATA_CHARS = "[CDATA[".toCharArray();
    // TODO: remove
    private static final char[] DOCTYPE_CHARS = "DOCTYPE".toCharArray();

    private boolean closed;

    private final PushbackReader in;
    private final FullyReader fin;

    private final UntilCharReader endElementIn;
    private final UntilCharSequenceReader commentIn;
    private final UntilCharSequenceReader cdataIn;
    private final UntilFilterReader processingInstructionTargetIn;
    private final UntilCharSequenceReader processingInstructionDataIn;
    private final UntilFilterReader startElementIn;
    private final UntilCharReader attributeNameIn;
    private final UntilCharReader attributeValueIn;
    private final UntilCharReader characterIn;

    private LWXMLEvent lastEvent;
    private long eventNumber = -1;

    private boolean handleAttributeList;
    private boolean handleEndEmptyElement;

    private Reader eventReader;

    // TODO: remove
    public LWXMLStreamReader(final InputStream in) {
        this(new BufferedReader(new InputStreamReader(in)));
    }

    public LWXMLStreamReader(final Reader in) {
        this.in = new PushbackReader(in, PUSHBACK_BUFFER_SIZE);
        this.fin = new FullyReader(this.in);

        this.endElementIn = new UntilCharReader(new ApplyFilterReader(this.fin, WHITESPACE_FILTER), '>');
        this.commentIn = new UntilCharSequenceReader(this.fin, "-->");
        this.cdataIn = new UntilCharSequenceReader(this.fin, "]]>");
        this.processingInstructionTargetIn = new UntilFilterReader(this.fin, WHITESPACE_FILTER);
        this.processingInstructionDataIn = new UntilCharSequenceReader(this.fin, "?>");
        this.startElementIn = new UntilFilterReader(this.fin, this.startElementFilter);
        this.attributeNameIn = new UntilCharReader(new ApplyFilterReader(this.fin, WHITESPACE_FILTER), '=');
        this.attributeValueIn = new UntilCharReader(this.fin, '"');
        this.characterIn = new UntilCharReader(this.in, '<');
    }

    @Override
    public LWXMLEvent getCurrentEvent() {
        return this.lastEvent;
    }

    @Override
    public long getCurrentEventNumber() {
        return this.eventNumber;
    }

    @Override
    public LWXMLEvent readEvent() throws IOException {
        return this.lastEvent = this.readNextEventImpl();
    }

    private LWXMLEvent readNextEventImpl() throws IOException {
        if (this.closed) {
            return LWXMLEvent.END_DOCUMENT;
        }
        if (this.eventReader != null) {
            CharStreamUtil.flushCharwise(this.eventReader);
        }

        this.eventNumber++;

        if (this.lastEvent != null) {
            switch (this.lastEvent) {
                case PROCESSING_INSTRUCTION_TARGET:
                    this.handleProcessingInstructionData();
                    return LWXMLEvent.PROCESSING_INSTRUCTION_DATA;
                case ATTRIBUTE_NAME:
                    this.handleAttributeValue();
                    return LWXMLEvent.ATTRIBUTE_VALUE;
                case CHARACTERS:
                    return this.handleElement();
                default:
                    break;
            }
        }

        if (this.handleAttributeList) {
            return this.handleAttributeList();
        }
        if (this.handleEndEmptyElement) {
            this.handleEndEmptyElement();
            return LWXMLEvent.END_EMPTY_ELEMENT;
        }

        final int read = this.in.read();
        switch (read) {
            case -1:
                this.close();
                return LWXMLEvent.END_DOCUMENT;
            case '<':
                return this.handleElement();
            default:
                this.in.unread(read);
                this.handleCharacters();
                return LWXMLEvent.CHARACTERS;
        }
    }

    private LWXMLEvent handleElement() throws IOException {
        final int c = this.in.read();

        switch (c) {
            case -1:
                this.close();
                return LWXMLEvent.END_DOCUMENT;
            case '/':
                this.handleEndElement();
                return LWXMLEvent.END_ELEMENT;
            case '!':
                return this.handleCallsign();
            case '?':
                this.handleProcessingInstructionTarget();
                return LWXMLEvent.PROCESSING_INSTRUCTION_TARGET;
            default:
                this.in.unread(c);
                this.handleStartElement();
                return LWXMLEvent.START_ELEMENT;
        }
    }

    // TODO: another way w/o new?
    private void handleEndElement() throws IOException {
        this.endElementIn.reset();
        this.eventReader = this.endElementIn;
    }

    private void handleEndEmptyElement() throws IOException {
        this.handleEndEmptyElement = false;
        this.eventReader = null;
    }

    // TODO: improve
    private LWXMLEvent handleCallsign() throws IOException {
        final int c = this.fin.read();

        switch (c) {
            case '-':
                if (this.fin.read() != '-') {
                    throw new LWXMLReaderException("malformend tag: comment was expected");
                }

                this.handleComment();
                return LWXMLEvent.COMMENT;
            case '[':
                if (!CharStreamUtil.matchChars(this.in, CDATA_CHARS, 1)) {
                    throw new LWXMLReaderException("malformed tag: cdata was expected");
                }

                this.handleCDATA();
                return LWXMLEvent.CDATA;
            case 'D':
                // TODO: remove
                if (!CharStreamUtil.matchChars(this.in, DOCTYPE_CHARS, 1)) {
                    throw new LWXMLReaderException("malformed tag: doctype expected");
                }

                // TODO: improve
                CharStreamUtil.flushUntilChar(this.fin, '>');
                return this.readEvent();
            default:
                throw new LWXMLReaderException("malformed tag: comment or cdata was expected");
        }
    }

    private void handleComment() throws IOException {
        this.commentIn.reset();
        this.eventReader = this.commentIn;
    }

    private void handleCDATA() throws IOException {
        this.cdataIn.reset();
        this.eventReader = this.cdataIn;
    }

    private void handleProcessingInstructionTarget() throws IOException {
        this.processingInstructionTargetIn.reset();
        this.eventReader = this.processingInstructionTargetIn;
    }

    private void handleProcessingInstructionData() throws IOException {
        CharStreamUtil.flushUntilFilter(this.in, WHITESPACE_FILTER);

        this.processingInstructionDataIn.reset();
        this.eventReader = this.processingInstructionDataIn;
    }

    private void handleStartElement() throws IOException {
        this.handleAttributeList = true;
        this.startElementIn.reset();
        this.eventReader = this.startElementIn;
    }

    private LWXMLEvent handleAttributeList() throws IOException {
        CharStreamUtil.flushUntilFilter(this.in, WHITESPACE_FILTER);
        final int c = this.fin.read();

        switch (c) {
            case '/':
                // TODO: flush whitespace?
                if (this.fin.read() != '>') {
                    throw new LWXMLReaderException("malformed tag: expected '>'");
                }
                this.handleEndEmptyElement = true;
            case '>':
                this.handleAttributeList = false;
                this.eventReader = null;
                return LWXMLEvent.END_ATTRIBUTE_LIST;
            default:
                this.in.unread(c);
                this.handleAttributeName();
                return LWXMLEvent.ATTRIBUTE_NAME;
        }
    }

    private void handleAttributeName() throws IOException {
        this.attributeNameIn.reset();
        this.eventReader = this.attributeNameIn;
    }

    // TODO: handle malformed xml
    private void handleAttributeValue() throws IOException {
        CharStreamUtil.flushUntilChar(this.in, '"');

        this.attributeValueIn.reset();
        this.eventReader = this.attributeValueIn;
    }

    private void handleCharacters() throws IOException {
        this.characterIn.reset();
        this.eventReader = this.characterIn;
    }

    @Override
    public int read() throws IOException {
        if (this.eventReader == null) {
            return -1;
        }
        return this.eventReader.read();
    }

    @Override
    public void close() throws IOException {
        if (this.closed) {
            return;
        }

        this.closed = true;
        this.eventReader = null;

        this.in.close();
    }

}