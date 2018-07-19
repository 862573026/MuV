// IRouterAIDL.aidl
package com.newx.api.router;
import com.newx.api.router.MaActionResult;
import com.newx.api.router.RouterRequest;
// Declare any non-default types here with import statements

interface IWideRouterAIDL {
    boolean checkResponseAsync(String domain,in RouterRequest routerRequset);
    MaActionResult route(String domain,in RouterRequest routerRequest);
    boolean stopRouter(String domain);
}
