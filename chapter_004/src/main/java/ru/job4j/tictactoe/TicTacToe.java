package ru.job4j.tictactoe;

/**
 * Tic tac toe - game main class.
 * @author aeremeev
 * @since 17.01.2018
 * @version 1
 */
public class TicTacToe implements Game {
    /**
     * First player.
     */
    private AbstractPlayer firstPlayer;
    /**
     * Second player.
     */
    private AbstractPlayer secondPlayer;
    /**
     * Field.
     */
    private final Field field;
    /**
     * Printer.
     */
    private final ConsolePrinter printer;

    /**
     * Constructor.
     * @param firstPlayer first player.
     * @param secondPlayer second player.
     * @param field field.
     * @param printer printer.
     */
    public TicTacToe(AbstractPlayer firstPlayer, AbstractPlayer secondPlayer, Field field, ConsolePrinter printer) {
        this.firstPlayer = firstPlayer;
        this.secondPlayer = secondPlayer;
        this.field = field;
        this.printer = printer;
    }

    /**
     * Switch players.
     */
    public void switchPlayers() {
        AbstractPlayer swap;
        swap = this.firstPlayer;
        this.firstPlayer = this.secondPlayer;
        this.secondPlayer = swap;
    }

    /**
     * Initialize game.
     */
    @Override
    public void init() {
        this.field.fillGameField();
        this.printer.printGameField(this.field.getGameField());
    }

    /**
     * Play one round.
     */
    @Override
    public void playRound() {
        this.init();
        while (!this.field.emptyCells().isEmpty()) {
            int firstMove = -1;
            int secondMove = -1;
            printer.printMessage(String.format("%s's move: ", firstPlayer.getName()));
            while (firstMove == -1) {
                firstMove = firstPlayer.move();
            }
            this.field.getGameField()[firstMove].setMark(firstPlayer.getMark());
            printer.printGameField(this.field.getGameField());
            printer.printMessage(String.format("%s's move: ", secondPlayer.getName()));
            if (firstPlayer.isWinner()) {
                firstPlayer.increaseWinCount();
                break;
            }
            while (secondMove == -1) {
                secondMove = secondPlayer.move();
            }
            this.field.getGameField()[secondMove].setMark(secondPlayer.getMark());
            printer.printGameField(this.field.getGameField());
            if (secondPlayer.isWinner()) {
                secondPlayer.increaseWinCount();
                break;
            }
        }
        printer.printMessage(String.format("Round score: %s : %d | %s %d", firstPlayer.getName(),
                firstPlayer.getWinCount(), secondPlayer.getName(), secondPlayer.getWinCount()));
    }

    /**
     * Play multiple rounds.
     * @param rounds rounds count.
     */
    @Override
    public void playMultipleRounds(int rounds) {
        int playedGames = 0;
        int prematureVictory = (rounds / 2) + 1;
        for (int index = 0; index != rounds; index++) {
            this.playRound();
            playedGames++;
            if (this.firstPlayer.getWinCount() == prematureVictory
                   || this.secondPlayer.getWinCount() == prematureVictory) {
                break;
            }
            this.switchPlayers();
        }
        printer.printMessage(String.format("Final score after %d round(s): %s : %d | %s %d", playedGames,
                firstPlayer.getName(), firstPlayer.getWinCount(), secondPlayer.getName(), secondPlayer.getWinCount()));
    }

    /**
     * Main.
     * @param args args.
     */
    public static void main(String[] args) {
        Field field = new Field(3);
        ConsolePrinter printer = new ConsolePrinter();
        AbstractPlayer first = new Human("Ordinary Human", Mark.X, field);
        AbstractPlayer second = new DumbComputer("Dumb Computer", Mark.O, field);

        Game ttt = new TicTacToe(first, second, field, printer);
        ttt.playMultipleRounds(3);
    }
}
