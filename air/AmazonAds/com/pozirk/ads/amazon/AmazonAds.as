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

package com.pozirk.ads.amazon
{
	import flash.events.EventDispatcher;
	import flash.events.StatusEvent;
	import flash.external.ExtensionContext;
	import flash.system.Capabilities;
	
	/**
	 * Main AmazonAds class
	 * @author Pozirk Games (http://www.pozirk.com)
	 */
	public class AmazonAds extends EventDispatcher
	{
		protected var _ctx:ExtensionContext;

		public function AmazonAds()
		{
			_ctx = ExtensionContext.createExtensionContext("com.pozirk.ads.AmazonAds", null);
			if(_ctx != null)
				_ctx.addEventListener(StatusEvent.STATUS, onStatus);
			else
				trace("Error! ANE file was not properly added to your project.");
		}

		public function init(appID:String):void
		{
			_ctx.call("init", appID);
		}
		
		/**
		 * Show ad
		 * @param	size - one of the constants from AdParams
		 * @param	halign - left, center, right,  from AdParams
		 * @param	valign - from AdParams
		 */
		public function show(size:int, halign:int, valign:int):void
		{
			_ctx.call("show", size, halign, valign);
		}
		
		public function hide():void
		{
			_ctx.call("hide");
		}
		
		/**
		 * Cache interstitial ad, listen for AdEvent.INTERSTITIAL_CACHE_OK before showing it
		 */
		public function cacheInterstitial():void
		{
			_ctx.call("cacheInterstitial");
		}

		/**
		 * Show interstitial ad, if it is not cached yet, nothing will be shown
		 */
		public function showInterstitial():void
		{
			_ctx.call("showInterstitial");
		}
		
		public function setTimeout(timeout:int):void
		{
			_ctx.call("setTimeout", timeout);
		}
		
		public function enableTesting(enable:Boolean):void
		{
			_ctx.call("enableTesting", enable);
		}
		
		public function enableLogging(enable:Boolean):void
		{
			_ctx.call("enableLogging", enable);
		}

		protected function onStatus(event:StatusEvent):void
		{
			var e:AdEvent = null;
			//trace(event.code+event.level);
			switch(event.code)
			{
				case "AD_LOADED":
				{
					e = new AdEvent(AdEvent.AD_LOADED, event.level);
					break;
				}
				
				case "AD_EXPANDED":
				{
					e = new AdEvent(AdEvent.AD_EXPANDED, event.level);
					break;
				}
				
				case "AD_COLLAPSED":
				{
					e = new AdEvent(AdEvent.AD_COLLAPSED, event.level);
					break;
				}
				
				case "AD_FAILED_TO_LOAD":
				{
					e = new AdEvent(AdEvent.AD_FAILED_TO_LOAD, event.level);
					break;
				}
				
				case "AD_DISMISSED":
				{
					e = new AdEvent(AdEvent.AD_DISMISSED, event.level);
					break;
				}
			}
			
			if(e != null)
				this.dispatchEvent(e);
		}

		public function dispose():void
		{
			_ctx.call("dispose");
			_ctx.dispose();
		}
	}
}
