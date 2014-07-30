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
	import flash.events.Event;

	/**
	 * Ad events
	 * @author Pozirk Games (http://www.pozirk.com)
	 */
	public class AdEvent extends Event
	{
		public static const AD_LOADED:String = "AD_LOADED"; //and shown
		public static const AD_EXPANDED:String = "AD_EXPANDED"; //user clicked banner
		public static const AD_COLLAPSED:String = "AD_COLLAPSED"; //user closed rich media ad
		public static const AD_FAILED_TO_LOAD:String = "AD_FAILED_TO_LOAD"; //shit happens
		public static const AD_DISMISSED:String = "AD_DISMISSED"; //most likely interstitial was closed by user
		
		public var _data:String; //extra info about event
		
		public function AdEvent(type:String, data:String = null)
		{
			super(type, false, false);
			_data = data;
		}
	}
}
