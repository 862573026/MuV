package com.newx.api.router;
import com.newx.api.router.MaActionResult;
import com.newx.api.router.RouterRequest;

interface ILocalRouterAIDL {
    boolean checkResponseAsync(in RouterRequest routerRequset);
    MaActionResult route(in RouterRequest routerRequest);
    boolean stopWideRouter();
    void connectWideRouter();
}
