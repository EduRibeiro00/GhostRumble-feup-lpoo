package com.aor.ghostrumble.view;

import com.aor.ghostrumble.controller.Event;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.io.IOException;

import static org.junit.Assert.*;

public class GameLanternaTest {

    private GameLanterna game;

    @Before
    public void init() throws IOException {
        game = new GameLanterna();
    }

    @Test
    public void testCreateDrawingMethod() {
        assertTrue(game.createDrawingMethod() instanceof DrawLanterna);
    }

    @Test
    public void testCreateEventEscape() throws IOException {
        Screen screen = Mockito.mock(Screen.class);
        game.setScreen(screen);

        KeyStroke key = Mockito.mock(KeyStroke.class);
        Mockito.when(key.getKeyType()).thenReturn(KeyType.Escape);

        Mockito.when(screen.readInput()).thenReturn(key);

        Event event = new Event(Event.TYPE.NO_EVENT);

        assertTrue(game.handleInput(event));
        assertEquals(Event.TYPE.CLOSE, event.getType());
    }

    @Test
    public void testCreateEventEOF() throws IOException {
        Screen screen = Mockito.mock(Screen.class);
        game.setScreen(screen);

        KeyStroke key = Mockito.mock(KeyStroke.class);
        Mockito.when(key.getKeyType()).thenReturn(KeyType.EOF);

        Mockito.when(screen.readInput()).thenReturn(key);

        Event event = new Event(Event.TYPE.NO_EVENT);

        assertFalse(game.handleInput(event));
        assertEquals(Event.TYPE.EXIT, event.getType());
    }

    @Test
    public void testCreateEventW() throws IOException {
        Screen screen = Mockito.mock(Screen.class);
        game.setScreen(screen);

        KeyStroke key = Mockito.mock(KeyStroke.class);
        Mockito.when(key.getKeyType()).thenReturn(KeyType.Character);
        Mockito.when(key.getCharacter()).thenReturn('w');

        Mockito.when(screen.readInput()).thenReturn(key);

        Event event = new Event(Event.TYPE.NO_EVENT);

        assertTrue(game.handleInput(event));
        assertEquals(Event.TYPE.PLAYER_UP, event.getType());
    }

    @Test
    public void testEventIrrelevantCharacter() throws IOException {
        Screen screen = Mockito.mock(Screen.class);
        game.setScreen(screen);

        KeyStroke key = Mockito.mock(KeyStroke.class);
        Mockito.when(key.getKeyType()).thenReturn(KeyType.Character);
        Mockito.when(key.getCharacter()).thenReturn('k');

        Mockito.when(screen.readInput()).thenReturn(key);

        Event event = new Event(Event.TYPE.NO_EVENT);

        assertTrue(game.handleInput(event));
        assertEquals(Event.TYPE.NO_EVENT, event.getType());
    }

    @Test
    public void testEventIrrelevantKeyType() throws IOException {
        Screen screen = Mockito.mock(Screen.class);
        game.setScreen(screen);

        KeyStroke key = Mockito.mock(KeyStroke.class);
        Mockito.when(key.getKeyType()).thenReturn(KeyType.Enter);

        Mockito.when(screen.readInput()).thenReturn(key);

        Event event = new Event(Event.TYPE.NO_EVENT);

        assertTrue(game.handleInput(event));
        assertEquals(Event.TYPE.NO_EVENT, event.getType());
    }
}