package at.stefl.commons.util.object;

public interface ObjectTransformer<S, D> {

    D transform(S from);

}