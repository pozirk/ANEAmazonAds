/* Copyright (c) 2014 Pozirk Games
 * http://www.pozirk.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.pozirk.ads.amazon;

import android.app.Activity;
//import android.util.Log;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.amazon.device.ads.*;

public class AmazonAdsManager implements AdListener
{
  protected AdLayout _adView = null;
  protected InterstitialAd _interstitial;
  protected AdSize _adSize = AdSize.SIZE_AUTO;
  protected RelativeLayout _parentView;
  protected Activity _act;
  protected ExtensionContext _ctx;
  protected boolean _testing = false;
  protected boolean _async = false;

  public AmazonAdsManager(String appID, Activity act, ExtensionContext ctx)
  {
	  _act = act;
	  _ctx = ctx;

	  RelativeLayout layout = new RelativeLayout(_act);
	  _act.addContentView(layout, new ViewGroup.LayoutParams(-1, -1));

	  AdRegistration.setAppKey(appID);

	  _parentView = layout;
  }
  
  public void show(int size, int halign, int valign)
  {
  	hide();
  	
  	RelativeLayout.LayoutParams params;
  	switch(size)
  	{
  	case 1:
  		_adSize = AdSize.SIZE_1024x50;
  		params = new RelativeLayout.LayoutParams(1024, 50);
  		break;
  	case 2:
  		_adSize = AdSize.SIZE_300x250;
  		params = new RelativeLayout.LayoutParams(300, 520);
  		break;
  	case 3:
  		_adSize = AdSize.SIZE_300x50;
  		params = new RelativeLayout.LayoutParams(300, 50);
  		break;
  	case 4:
  		_adSize = AdSize.SIZE_320x50;
  		params = new RelativeLayout.LayoutParams(320, 50);
  		break;
  	case 5:
  		_adSize = AdSize.SIZE_600x90;
  		params = new RelativeLayout.LayoutParams(600, 90);
  		break;
  	case 6:
  		_adSize = AdSize.SIZE_728x90;
  		params = new RelativeLayout.LayoutParams(728, 90);
  		break;
  	default:
  		_adSize = AdSize.SIZE_AUTO;
  		params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
  	}
  	
  	_adView = new AdLayout(_act, _adSize);

  	_adView.setListener(this);

  	params.addRule(halign, -1);
  	params.addRule(valign, -1);
  	_parentView.addView(_adView, params);
  	
  	AdTargetingOptions adOptions = new AdTargetingOptions(); //use default, but could set age and gender
  	_async = _adView.loadAd(adOptions);
  }
  
  public void cacheInterstitial()
  {
	  _interstitial = new InterstitialAd(_act);
	  
      // Set the listener to use the callbacks below.
      _interstitial.setListener(this);

      // Load the interstitial.
      _interstitial.loadAd();
  }
  
  public void showInterstitial()
  {
  	if(_interstitial != null)
  		_interstitial.showAd();
  }
  
  public void setTimeout(int timeout)
  {
  	_adView.setTimeout(timeout);
  }
  
  public void enableTesting(boolean enable)
  {
  	_testing = enable;
  	AdRegistration.enableTesting(enable);
  }
  
  public void enableLogging(boolean enable)
  {
  	AdRegistration.enableLogging(enable);
  }

  public void hide()
  {
  	if(_adView != null)
  	{
  		_parentView.removeView(_adView);
  	
  		_adView.destroy();
  	}
  	
  	_adView = null;
  }

  public void dispose()
  {
  	hide();
  }

  public void onAdLoaded(Ad ad, AdProperties adProperties)
  {
  	if(_ctx != null)
  		_ctx.dispatchStatusEventAsync("AD_LOADED", "type: "+(ad == _adView ? "banner" : "interstitial")+", testing: "+_testing+", asyncTask: "+_async);
  }

  public void onAdFailedToLoad(Ad ad, AdError error)
  {
  	if(_ctx != null)
      _ctx.dispatchStatusEventAsync("AD_FAILED_TO_LOAD", "type: "+(ad == _adView ? "banner" : "interstitial")+", "+error.getCode()+": "+error.getMessage());
  }

  public void onAdExpanded(Ad ad)
  {
  	if(_ctx != null)
      _ctx.dispatchStatusEventAsync("AD_EXPANDED", "type: "+(ad == _adView ? "banner" : "interstitial"));
  }

  public void onAdCollapsed(Ad ad)
  {
  	if(_ctx != null)
      _ctx.dispatchStatusEventAsync("AD_COLLAPSED", "type: "+(ad == _adView ? "banner" : "interstitial"));
  }
  
  public void onAdDismissed(Ad ad)
  {
  	if(_ctx != null)
  		_ctx.dispatchStatusEventAsync("AD_DISMISSED", "type: "+(ad == _adView ? "banner" : "interstitial"));
  }
}