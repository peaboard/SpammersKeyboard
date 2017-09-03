package com.prabodhs.spammerskeyboard;

import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.inputmethodservice.InputMethodService;
import android.inputmethodservice.Keyboard;
import android.inputmethodservice.KeyboardView;
import android.media.AudioManager;
import android.os.IBinder;
import android.os.SystemClock;
import android.preference.PreferenceManager;
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

    // Private declaration for function-wide declarations
    private Boolean motoExp;
    private Boolean otherExp;
    SharedPreferences sharedPref;

    //private ModifierKeyState mCtrlKeyState = new ModifierKeyState();

    private boolean caps = false;

    private void sendKeyDown(InputConnection ic, int key, int meta) {
        long now = System.currentTimeMillis();
        if (ic != null) ic.sendKeyEvent(new KeyEvent(
                now, now, KeyEvent.ACTION_DOWN, key, 0, meta));
    }




    @Override
    public View onCreateInputView() {

        sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        motoExp = sharedPref.getBoolean("moto_exp", false);
        otherExp = sharedPref.getBoolean("other_exp", false);

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



//        motoExp = true;
//        otherExp = false;

        /* For Debugging */
        //Log.d("STATE", String.valueOf(motoExp));

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

                // Will have to change this such that only one flag can be active at a given time
                if(otherExp)
                {
                    for(int l=0; l<=25; l++){
                        SystemClock.sleep(10);
                        getCurrentInputConnection().sendKeyEvent(new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_E));
                        getCurrentInputConnection().sendKeyEvent(new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_DEL));
                        SystemClock.sleep(10);
                        //getCurrentInputConnection().sendKeyEvent(new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_PASTE));
                        ic.sendKeyEvent(new KeyEvent(0,0,KeyEvent.ACTION_DOWN,KeyEvent.KEYCODE_V,0,KeyEvent.META_CTRL_ON));
                        SystemClock.sleep(10);
                        keyDownUp(KeyEvent.KEYCODE_ENTER);
                        SystemClock.sleep(500);
                        getCurrentInputConnection().sendKeyEvent(new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_TAB));
                        getCurrentInputConnection().sendKeyEvent(new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_TAB));
                        getCurrentInputConnection().sendKeyEvent(new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_TAB));
                        keyDownUp(KeyEvent.KEYCODE_ENTER);
                        SystemClock.sleep(10);
                    }
                }
                else if (motoExp)
                {
                    for(int l=0; l<=25; l++){
                        SystemClock.sleep(10);
                        mc.sendKeyEvent(new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_E));
                        SystemClock.sleep(100);
                        getCurrentInputConnection().sendKeyEvent(new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_DEL));
                        SystemClock.sleep(100);
                        //getCurrentInputConnection().sendKeyEvent(new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_PASTE));
                        ic.sendKeyEvent(new KeyEvent(0,0,KeyEvent.ACTION_DOWN,KeyEvent.KEYCODE_V,0,KeyEvent.META_CTRL_ON));
                        SystemClock.sleep(100);
                        keyDownUp(KeyEvent.KEYCODE_ENTER);
                        SystemClock.sleep(200);
                        getCurrentInputConnection().sendKeyEvent(new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_TAB));
                        keyDownUp(KeyEvent.KEYCODE_ENTER);
                        SystemClock.sleep(10);
                    }
                }
                else
                {
                    for(int l=0; l<=25; l++){
                        getCurrentInputConnection().sendKeyEvent(new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_E));
                        getCurrentInputConnection().sendKeyEvent(new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_DEL));
                        ic.sendKeyEvent(new KeyEvent(0,0,KeyEvent.ACTION_DOWN,KeyEvent.KEYCODE_V,0,KeyEvent.META_CTRL_ON));
                        SystemClock.sleep(10);
                        keyDownUp(KeyEvent.KEYCODE_ENTER);
                        SystemClock.sleep(200);
                    }
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
