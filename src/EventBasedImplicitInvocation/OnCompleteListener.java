package EventBasedImplicitInvocation;

import java.util.EventListener;

public interface OnCompleteListener extends EventListener {
    void onComplete(KWICResult result);
}
