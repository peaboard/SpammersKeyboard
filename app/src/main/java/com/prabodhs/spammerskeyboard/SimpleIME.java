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

//                for(int l=0; l<=25; l++){
//                    ic.sendKeyEvent(new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_PASTE));
//                }

                //ic.sendKeyEvent(new KeyEvent(KeyEvent.ACTION_DOWN, 113));

                //int key = KeyEvent.KEYCODE_CTRL_LEFT;
                //int meta = KeyEvent.META_CTRL_ON | KeyEvent.META_CTRL_LEFT_ON;

                //ic.sendKeyEvent(new KeyEvent(KeyEvent.ACTION_UP, -113));
                //ic.sendKeyEvent(new KeyEvent(KeyEvent.ACTION_DOWN, 113));
//                ic.sendKeyEvent(new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_V));
//                ic.sendKeyEvent(new KeyEvent(KeyEvent.ACTION_UP, KeyEvent.KEYCODE_V));

                ic.sendKeyEvent(new KeyEvent(0,0,KeyEvent.ACTION_DOWN,KeyEvent.KEYCODE_V,0,KeyEvent.META_CTRL_ON)) ;

                //sendKeyDown(ic, key, meta);

                //keyDownUp(KeyEvent.KEYCODE_V);

                //sendKeyUp(ic, key, meta);

                //ic.sendKeyEvent(new KeyEvent(KeyEvent.ACTION_UP, KeyEvent.META_CTRL_ON));


//                ic.sendKeyEvent(new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_PASTE));
//                ic.sendKeyEvent(new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_PASTE));
//                ic.sendKeyEvent(new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_PASTE));
//                ic.sendKeyEvent(new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_PASTE));
                break;
            case 193:

                //SystemClock.sleep(1000);

                //SystemClock.sleep(1000);

                //SystemClock.sleep(500);
                //keyDownUp(KeyEvent.KEYCODE_ENTER);
                //SystemClock.sleep(1000);


                for(int l=0; l<=25; l++){
                    SystemClock.sleep(10);
                    getCurrentInputConnection().sendKeyEvent(new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_E));
                    getCurrentInputConnection().sendKeyEvent(new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_DEL));
                    SystemClock.sleep(10);
                    //getCurrentInputConnection().sendKeyEvent(new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_PASTE));
                    ic.sendKeyEvent(new KeyEvent(0,0,KeyEvent.ACTION_DOWN,KeyEvent.KEYCODE_V,0,KeyEvent.META_CTRL_ON));
                    keyDownUp(KeyEvent.KEYCODE_ENTER);
                    SystemClock.sleep(500);
                    getCurrentInputConnection().sendKeyEvent(new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_TAB));
                    getCurrentInputConnection().sendKeyEvent(new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_TAB));
                    getCurrentInputConnection().sendKeyEvent(new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_TAB));
                    keyDownUp(KeyEvent.KEYCODE_ENTER);
                    SystemClock.sleep(10);
                }



//                getCurrentInputConnection().sendKeyEvent(new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_PASTE));
////                SystemClock.sleep(500);
//                keyDownUp(KeyEvent.KEYCODE_ENTER);
//                getCurrentInputConnection().sendKeyEvent(new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_PASTE));
////                SystemClock.sleep(500);
//                keyDownUp(KeyEvent.KEYCODE_ENTER);
//                getCurrentInputConnection().sendKeyEvent(new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_PASTE));
////                SystemClock.sleep(500);
//                keyDownUp(KeyEvent.KEYCODE_ENTER);
//                getCurrentInputConnection().sendKeyEvent(new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_PASTE));
////                SystemClock.sleep(500);
//                keyDownUp(KeyEvent.KEYCODE_ENTER);
//                getCurrentInputConnection().sendKeyEvent(new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_PASTE));
////                SystemClock.sleep(500);
//                keyDownUp(KeyEvent.KEYCODE_ENTER);
//                getCurrentInputConnection().sendKeyEvent(new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_PASTE));
////                SystemClock.sleep(500);
//                keyDownUp(KeyEvent.KEYCODE_ENTER);
//                getCurrentInputConnection().sendKeyEvent(new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_PASTE));
////                SystemClock.sleep(500);
//                keyDownUp(KeyEvent.KEYCODE_ENTER);
//                getCurrentInputConnection().sendKeyEvent(new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_PASTE));
////                SystemClock.sleep(500);
//                keyDownUp(KeyEvent.KEYCODE_ENTER);
//                InputConnection bc = getCurrentInputConnection();
//                keyDownUp(KeyEvent.KEYCODE_ENTER);
////                keyDownUp(KeyEvent.KEYCODE_ENTER);
//                getCurrentInputConnection().sendKeyEvent(new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_TAB));
//                keyDownUp(KeyEvent.KEYCODE_ENTER);
                //SystemClock.sleep(500);
//                ic.sendKeyEvent(new KeyEvent(KeyEvent.ACTION_DOWN, (KeyEvent.KEYCODE_ENTER)));
//                editText.setImeOptions(EditorInfo.IME_ACTION_DONE);

//                ic.sendKeyEvent(new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_PASTE));
//                ic.sendKeyEvent(new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_ENTER));
//
//                ic.sendKeyEvent(new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_PASTE));
//                ic.sendKeyEvent(new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_ENTER));
//
//                ic.sendKeyEvent(new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_PASTE));
//                ic.sendKeyEvent(new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_ENTER));
//
//                ic.sendKeyEvent(new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_PASTE));
//                ic.sendKeyEvent(new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_ENTER));

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
//                char code = (char)primaryCode;
//                if(Character.isLetter(code) && caps){
//                    code = Character.toUpperCase(code);
//                }
//                ic.commitText(String.valueOf(code),1);
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
