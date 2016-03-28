package android.support.v4.view;

import android.view.View;
import android.view.View.OnApplyWindowInsetsListener;
import android.view.WindowInsets;

class ViewCompatApi21 {

    /* renamed from: android.support.v4.view.ViewCompatApi21.1 */
    static class C00531 implements OnApplyWindowInsetsListener {
        final /* synthetic */ OnApplyWindowInsetsListener val$listener;

        C00531(OnApplyWindowInsetsListener onApplyWindowInsetsListener) {
            this.val$listener = onApplyWindowInsetsListener;
        }

        public WindowInsets onApplyWindowInsets(View view, WindowInsets windowInsets) {
            return ((WindowInsetsCompatApi21) this.val$listener.onApplyWindowInsets(view, new WindowInsetsCompatApi21(windowInsets))).unwrap();
        }
    }

    ViewCompatApi21() {
    }

    public static void setTransitionName(View view, String transitionName) {
        view.setTransitionName(transitionName);
    }

    public static String getTransitionName(View view) {
        return view.getTransitionName();
    }

    public static void requestApplyInsets(View view) {
        view.requestApplyInsets();
    }

    public static void setElevation(View view, float elevation) {
        view.setElevation(elevation);
    }

    public static float getElevation(View view) {
        return view.getElevation();
    }

    public static void setTranslationZ(View view, float translationZ) {
        view.setTranslationZ(translationZ);
    }

    public static float getTranslationZ(View view) {
        return view.getTranslationZ();
    }

    public static void setOnApplyWindowInsetsListener(View view, OnApplyWindowInsetsListener listener) {
        view.setOnApplyWindowInsetsListener(new C00531(listener));
    }

    public static boolean isImportantForAccessibility(View view) {
        return view.isImportantForAccessibility();
    }
}
