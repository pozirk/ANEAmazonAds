# About
ANEAmazonAds is an Adobe AIR native extension (ANE) for Kindle tablets (and others Android devices) to show Amazon Ads.<br />
Supported functionality:<br />
- show ad;<br />
- hide ad;<br />
- listen tap, close, etc. event.<br />
<br />

# Docs
Please, read docs and try ANE before asking any questions.<br />
https://developer.amazon.com/sdk/mobileads.html<br />
http://help.adobe.com/en_US/air/extensions/index.html<br />


# Installation
Extension ID: com.pozirk.ads.AmazonAds<br />
Add "AmazonAds.ane" and "air\AdMob\bin\AmazonAds.swc" to your AIR project.<br />
Add the following lines to your AIR Aplication-app.xml file inside &lt;manifestAdditions&gt; section:<br />
<br />
&lt;uses-permission android:name="android.permission.INTERNET"/&gt;<br />
&lt;uses-permission android:name="android.permission.ACCESS_COARSE_LOCATION" /&gt;<br />
&lt;uses-permission android:name="android.permission.ACCESS_FINE_LOCATION" /&gt;<br />
&lt;uses-permission android:name="android.permission.ACCESS_NETWORK_STATE"/&gt;<br />
&lt;application&gt;<br />
	&lt;activity android:name="com.amazon.device.ads.AdActivity" android:configChanges="keyboardHidden|orientation|screenSize"/&gt;<br />
&lt;/application&gt;<br />

Internet permission is mandatory, others are optional.


# Example
```actionscript
import com.pozirk.ads.amazon.AmazonAds;
import com.pozirk.ads.amazon.AdParams;
import com.pozirk.ads.amazon.AdEvent;

...

protected var _amazonAds:AmazonAds = null;

...

//> initialization of AmazonAds
_amazonAds = new AmazonAds();
_amazonAds.addEventListener(AdEvent.AD_LOADED, onEvent);
_amazonAds.addEventListener(AdEvent.AD_FAILED_TO_LOAD, onEvent);
_amazonAds.addEventListener(AdEvent.AD_EXPANDED, onEvent);
_amazonAds.addEventListener(AdEvent.AD_COLLAPSED, onEvent);
_amazonAds.init("YOUR_APP_ID");
_amazonAds.enableLogging(true); //for debug only!
_amazonAds.enableTesting(true); //for debug only!
//<

...

protected function onEvent(ae:AdEvent):void
{
	trace(ae.type+" "+ae._data);
}
//<


//showing 600x90 ad at the bottom center side of the screen
_admob.show(AdParams.SIZE_600x90, AdParams.HALIGN_CENTER, AdParams.VALIGN_BOTTOM);
_amazonAds.setTimeout(30000); //set timeout (in milliseconds) to load new ad, not sure if it works properly...
```

# Misc
ANE is build with AIR3.9, in order to rebuild for another version do the following:<br />
- edit "air\extension.xml" and change 3.9 in very first line to any 3.X you need;<br />
- edit "build.bat" and in the very last line change path from AIR3.9 SDK to any AIR3.X SDK you need;<br />
- execute "build.bat" to repack the ANE.<br />