del android\AmazonAds\build\libAmazonAds.jar

del AmazonAds.ane

xcopy android\AmazonAds\bin\classes android\AmazonAds\bin /S /Y /R

rd android\AmazonAds\bin\classes /S /Q

"c:\Program Files (x86)\Java\jdk1.7.0_40\bin\jar.exe" cvf android/AmazonAds/build/libAmazonAds.jar -C android/AmazonAds/bin .

SET PLATFORM_ANDROID= -platform Android-ARM -C android\AmazonAds\build\ .
SET PLATFORM_DEFAULT= -platform default -C default\ .

"c:\Program Files (x86)\Adobe\AIR3.9\bin\adt.bat" -package -target ane AmazonAds.ane air\extension.xml -swc air/AmazonAds/bin/AmazonAds.swc %PLATFORM_ANDROID% %PLATFORM_DEFAULT%