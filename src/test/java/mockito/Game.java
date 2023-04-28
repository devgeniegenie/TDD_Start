package mockito;

public class Game {
    private final GameNumGen gameNumGen;

    public Game(GameNumGen gameNumGen) {
        this.gameNumGen = gameNumGen;
    }

    public void init(GameLevel level) {
        this.gameNumGen.generate(level);
    }
}
