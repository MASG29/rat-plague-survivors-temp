package ratplaguesurvivors.interfaces;

public interface State {
    // Not called every tick by the driver: render() only fires at state-entry
    // points (e.g. LOADING -> GAME), never inside the per-tick loop, to avoid
    // re-drawing/duplicating already-drawn Pictures.
    void updateState() throws InterruptedException;
    void renderState();
}
