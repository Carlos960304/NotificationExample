cordova.define('cordova/plugin_list', function(require, exports, module) {
  module.exports = [
    {
      "id": "cordova-plugin-geolocation.geolocation",
      "file": "plugins/cordova-plugin-geolocation/www/android/geolocation.js",
      "pluginId": "cordova-plugin-geolocation",
      "clobbers": [
        "navigator.geolocation"
      ]
    },
    {
      "id": "cordova-plugin-geolocation.PositionError",
      "file": "plugins/cordova-plugin-geolocation/www/PositionError.js",
      "pluginId": "cordova-plugin-geolocation",
      "runs": true
    },
    {
      "id": "cordova-plugin-foreground-service.ForegroundService",
      "file": "plugins/cordova-plugin-foreground-service/www/foreground.js",
      "pluginId": "cordova-plugin-foreground-service",
      "clobbers": [
        "cordova.plugins.foregroundService"
      ]
    }
  ];
  module.exports.metadata = {
    "cordova-plugin-whitelist": "1.3.5",
    "cordova-plugin-geolocation": "4.1.0",
    "cordova-plugin-foreground-service": "1.1.3"
  };
});