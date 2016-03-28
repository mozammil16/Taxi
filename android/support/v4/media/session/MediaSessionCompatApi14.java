package android.support.v4.media.session;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.RemoteControlClient;
import android.media.RemoteControlClient.MetadataEditor;
import android.os.Bundle;
import android.os.ResultReceiver;

public class MediaSessionCompatApi14 {
    private static final String METADATA_KEY_ALBUM = "android.media.metadata.ALBUM";
    private static final String METADATA_KEY_ALBUM_ARTIST = "android.media.metadata.ALBUM_ARTIST";
    private static final String METADATA_KEY_ARTIST = "android.media.metadata.ARTIST";
    private static final String METADATA_KEY_AUTHOR = "android.media.metadata.AUTHOR";
    private static final String METADATA_KEY_COMPILATION = "android.media.metadata.COMPILATION";
    private static final String METADATA_KEY_COMPOSER = "android.media.metadata.COMPOSER";
    private static final String METADATA_KEY_DATE = "android.media.metadata.DATE";
    private static final String METADATA_KEY_DISC_NUMBER = "android.media.metadata.DISC_NUMBER";
    private static final String METADATA_KEY_DURATION = "android.media.metadata.DURATION";
    private static final String METADATA_KEY_GENRE = "android.media.metadata.GENRE";
    private static final String METADATA_KEY_NUM_TRACKS = "android.media.metadata.NUM_TRACKS";
    private static final String METADATA_KEY_TITLE = "android.media.metadata.TITLE";
    private static final String METADATA_KEY_TRACK_NUMBER = "android.media.metadata.TRACK_NUMBER";
    private static final String METADATA_KEY_WRITER = "android.media.metadata.WRITER";
    private static final String METADATA_KEY_YEAR = "android.media.metadata.YEAR";
    static final int RCC_PLAYSTATE_NONE = 0;
    static final int STATE_BUFFERING = 6;
    static final int STATE_CONNECTING = 8;
    static final int STATE_ERROR = 7;
    static final int STATE_FAST_FORWARDING = 4;
    static final int STATE_NONE = 0;
    static final int STATE_PAUSED = 2;
    static final int STATE_PLAYING = 3;
    static final int STATE_REWINDING = 5;
    static final int STATE_SKIPPING_TO_NEXT = 10;
    static final int STATE_SKIPPING_TO_PREVIOUS = 9;
    static final int STATE_STOPPED = 1;

    public interface Callback {
        void onCommand(String str, Bundle bundle, ResultReceiver resultReceiver);

        void onFastForward();

        boolean onMediaButtonEvent(Intent intent);

        void onPause();

        void onPlay();

        void onRewind();

        void onSeekTo(long j);

        void onSetRating(Object obj);

        void onSkipToNext();

        void onSkipToPrevious();

        void onStop();
    }

    public static Object createRemoteControlClient(PendingIntent mbIntent) {
        return new RemoteControlClient(mbIntent);
    }

    public static void setState(Object rccObj, int state) {
        ((RemoteControlClient) rccObj).setPlaybackState(getRccStateFromState(state));
    }

    public static void setMetadata(Object rccObj, Bundle metadata) {
        MetadataEditor editor = ((RemoteControlClient) rccObj).editMetadata(true);
        buildOldMetadata(metadata, editor);
        editor.apply();
    }

    public static void registerRemoteControlClient(Context context, Object rccObj) {
        ((AudioManager) context.getSystemService("audio")).registerRemoteControlClient((RemoteControlClient) rccObj);
    }

    public static void unregisterRemoteControlClient(Context context, Object rccObj) {
        ((AudioManager) context.getSystemService("audio")).unregisterRemoteControlClient((RemoteControlClient) rccObj);
    }

    static int getRccStateFromState(int state) {
        switch (state) {
            case STATE_NONE /*0*/:
                return STATE_NONE;
            case STATE_STOPPED /*1*/:
                return STATE_STOPPED;
            case STATE_PAUSED /*2*/:
                return STATE_PAUSED;
            case STATE_PLAYING /*3*/:
                return STATE_PLAYING;
            case STATE_FAST_FORWARDING /*4*/:
                return STATE_FAST_FORWARDING;
            case STATE_REWINDING /*5*/:
                return STATE_REWINDING;
            case STATE_BUFFERING /*6*/:
            case STATE_CONNECTING /*8*/:
                return STATE_CONNECTING;
            case STATE_ERROR /*7*/:
                return STATE_SKIPPING_TO_PREVIOUS;
            case STATE_SKIPPING_TO_PREVIOUS /*9*/:
                return STATE_ERROR;
            case STATE_SKIPPING_TO_NEXT /*10*/:
                return STATE_BUFFERING;
            default:
                return -1;
        }
    }

    static void buildOldMetadata(Bundle metadata, MetadataEditor editor) {
        if (metadata.containsKey(METADATA_KEY_ALBUM)) {
            editor.putString(STATE_STOPPED, metadata.getString(METADATA_KEY_ALBUM));
        }
        if (metadata.containsKey(METADATA_KEY_ALBUM_ARTIST)) {
            editor.putString(13, metadata.getString(METADATA_KEY_ALBUM_ARTIST));
        }
        if (metadata.containsKey(METADATA_KEY_ARTIST)) {
            editor.putString(STATE_PAUSED, metadata.getString(METADATA_KEY_ARTIST));
        }
        if (metadata.containsKey(METADATA_KEY_AUTHOR)) {
            editor.putString(STATE_PLAYING, metadata.getString(METADATA_KEY_AUTHOR));
        }
        if (metadata.containsKey(METADATA_KEY_COMPILATION)) {
            editor.putString(15, metadata.getString(METADATA_KEY_COMPILATION));
        }
        if (metadata.containsKey(METADATA_KEY_COMPOSER)) {
            editor.putString(STATE_FAST_FORWARDING, metadata.getString(METADATA_KEY_COMPOSER));
        }
        if (metadata.containsKey(METADATA_KEY_DATE)) {
            editor.putString(STATE_REWINDING, metadata.getString(METADATA_KEY_DATE));
        }
        if (metadata.containsKey(METADATA_KEY_DISC_NUMBER)) {
            editor.putLong(14, metadata.getLong(METADATA_KEY_DISC_NUMBER));
        }
        if (metadata.containsKey(METADATA_KEY_DURATION)) {
            editor.putLong(STATE_SKIPPING_TO_PREVIOUS, metadata.getLong(METADATA_KEY_DURATION));
        }
        if (metadata.containsKey(METADATA_KEY_GENRE)) {
            editor.putString(STATE_BUFFERING, metadata.getString(METADATA_KEY_GENRE));
        }
        if (metadata.containsKey(METADATA_KEY_NUM_TRACKS)) {
            editor.putLong(STATE_SKIPPING_TO_NEXT, metadata.getLong(METADATA_KEY_NUM_TRACKS));
        }
        if (metadata.containsKey(METADATA_KEY_TITLE)) {
            editor.putString(STATE_ERROR, metadata.getString(METADATA_KEY_TITLE));
        }
        if (metadata.containsKey(METADATA_KEY_TRACK_NUMBER)) {
            editor.putLong(STATE_NONE, metadata.getLong(METADATA_KEY_TRACK_NUMBER));
        }
        if (metadata.containsKey(METADATA_KEY_WRITER)) {
            editor.putString(11, metadata.getString(METADATA_KEY_WRITER));
        }
        if (metadata.containsKey(METADATA_KEY_YEAR)) {
            editor.putString(STATE_CONNECTING, metadata.getString(METADATA_KEY_YEAR));
        }
    }
}
