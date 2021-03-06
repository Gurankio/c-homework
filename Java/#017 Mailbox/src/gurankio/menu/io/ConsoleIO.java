package gurankio.menu.io;

import gurankio.util.CharPacks;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
import java.util.NoSuchElementException;
import java.util.concurrent.ConcurrentLinkedDeque;

public class ConsoleIO extends MenuIO {

    private final BufferedReader READER;

    public ConsoleIO() {
        READER = new BufferedReader(new InputStreamReader(System.in));
    }

    @Override
    public String read(String prompt) {
        print(prompt);
        try {
            return READER.readLine().strip();
        } catch (IOException ignored) {
            return "";
        }
    }

    @Override
    public void print(String data) {
        data.lines().forEach(l -> System.out.print(CharPacks.selected.getSpacer(getIndentation() * CharPacks.WIDTH) + l));
    }

    @Override
    public void println(String data) {
        data.lines().forEach(l -> System.out.print(CharPacks.selected.getSpacer(getIndentation() * CharPacks.WIDTH) + l + System.lineSeparator()));
    }

}
