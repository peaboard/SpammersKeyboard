package com.prabodhs.spammerskeyboard;

import android.app.Service;
import android.content.Intent;
import android.inputmethodservice.InputMethodService;
import android.inputmethodservice.Keyboard;
import android.inputmethodservice.KeyboardView;
import android.media.AudioManager;
import android.os.IBinder;
import android.os.SystemClock;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.widget.EditText;

public class SimpleIME extends InputMethodService
        implements KeyboardView.OnKeyboardActionListener {

    private KeyboardView kv;
    private Keyboard keyboard;
    private EditText editText;

    //private ModifierKeyState mCtrlKeyState = new ModifierKeyState();

    private boolean caps = false;


    private void sendKeyDown(InputConnection ic, int key, int meta) {
        long now = System.currentTimeMillis();
        if (ic != null) ic.sendKeyEvent(new KeyEvent(
                now, now, KeyEvent.ACTION_DOWN, key, 0, meta));
    }


    @Override
    public View onCreateInputView() {
        kv = (KeyboardView)getLayoutInflater().inflate(R.layout.keyboard, null);
        keyboard = new Keyboard(this, R.xml.qwerty);
        EditText editText = new EditText(this);
        kv.setKeyboard(keyboard);
        kv.setOnKeyboardActionListener(this);
        //kv.setPreviewEnabled(false);
        return kv;
    }

    private void playClick(int keyCode){
        AudioManager am = (AudioManager)getSystemService(AUDIO_SERVICE);
        switch(keyCode){
            case 32:
                am.playSoundEffect(AudioManager.FX_KEYPRESS_SPACEBAR);
                break;
            case Keyboard.KEYCODE_DONE:
            case 10:
                am.playSoundEffect(AudioManager.FX_KEYPRESS_RETURN);
                break;
            case Keyboard.KEYCODE_DELETE:
                am.playSoundEffect(AudioManager.FX_KEYPRESS_DELETE);
                break;
            default: am.playSoundEffect(AudioManager.FX_KEYPRESS_STANDARD);
        }
    }

    private void keyDownUp(int keyEventCode) {
        getCurrentInputConnection().sendKeyEvent(
                new KeyEvent(KeyEvent.ACTION_DOWN, keyEventCode));
        getCurrentInputConnection().sendKeyEvent(
                new KeyEvent(KeyEvent.ACTION_UP, keyEventCode));
    }

    private void sendKeyUp(InputConnection ic, int key, int meta) {
        long now = System.currentTimeMillis();
        if (ic != null) ic.sendKeyEvent(new KeyEvent(
                now, now, KeyEvent.ACTION_UP, key, 0, meta));
    }

    @Override
    public void onKey(int primaryCode, int[] keyCodes) {
        InputConnection ic = getCurrentInputConnection();
        InputConnection mc = getCurrentInputConnection();
        //playClick(primaryCode);
        switch(primaryCode){
            case Keyboard.KEYCODE_DELETE :
                ic.deleteSurroundingText(1000, 0);
                break;
            case Keyboard.KEYCODE_SHIFT:
                caps = !caps;
                keyboard.setShifted(caps);
                kv.invalidateAllKeys();
                break;
            case Keyboard.KEYCODE_DONE:
                ic.sendKeyEvent(new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.FLAG_EDITOR_ACTION));
                break;
            case 192:

                for(int l=0; l<=25; l++){
                    ic.sendKeyEvent(new KeyEvent(0,0,KeyEvent.ACTION_DOWN,KeyEvent.KEYCODE_V,0,KeyEvent.META_CTRL_ON)) ;
                }
                break;

            case 193:

                for(int l=0; l<=25; l++){
                    getCurrentInputConnection().sendKeyEvent(new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_E));
                    getCurrentInputConnection().sendKeyEvent(new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_DEL));
                    ic.sendKeyEvent(new KeyEvent(0,0,KeyEvent.ACTION_DOWN,KeyEvent.KEYCODE_V,0,KeyEvent.META_CTRL_ON));
                    SystemClock.sleep(10);
                    keyDownUp(KeyEvent.KEYCODE_ENTER);
                    SystemClock.sleep(200);
                }

                break;
            case 194:
                ic.sendKeyEvent(new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_TAB));
                break;
            case 195:
                keyDownUp(KeyEvent.KEYCODE_ENTER);
                break;
            case 197:
                keyDownUp(KeyEvent.KEYCODE_ENTER);
                editText.setImeOptions(EditorInfo.IME_ACTION_DONE);
                break;
            case 196:
                editText.setImeOptions(EditorInfo.IME_ACTION_DONE);
                break;
            default:
                break;
        }
    }

    @Override
    public void onPress(int primaryCode) {
    }

    @Override
    public void onRelease(int primaryCode) {
    }

    @Override
    public void onText(CharSequence text) {
    }

    @Override
    public void swipeDown() {
    }

    @Override
    public void swipeLeft() {
    }

    @Override
    public void swipeRight() {
    }

    @Override
    public void swipeUp() {
    }


}
