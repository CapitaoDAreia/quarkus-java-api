package code.challenge.app.usecases;

public abstract class UseCase<INPUT, OUTPUT> {
    public abstract OUTPUT execute(INPUT input);
}
