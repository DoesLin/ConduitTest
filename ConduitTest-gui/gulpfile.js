var fs = require('fs');
var packageJson = JSON.parse(fs.readFileSync('./package.json'));
var config = {
  appDir: './dist',
  appVersion: packageJson.version,
  appName: packageJson.name,
  finalZipName: 'dist'
};
var gulp = require('gulp'),
  maven = require('maven-deploy');
var mavenConfig = {
  "groupId": "polytech.tours",
  "artifactId": config.appName,
  "version": config.appVersion,
  "buildDir": config.appDir,
  "finalName": config.finalZipName,
  "type": "zip",
  "fileEncoding": "utf-8",
  "repositories": [{
    "id": "conduittest-release-repo",
    "url": "./releases"
  }, {
    "id": "conduittest-snapshot-repo",
    "url": "./snapshots"
  }]
};
maven.config(mavenConfig);
gulp.task('install', function () {
  maven.install();
});
gulp.task('deploy', function () {
  var isSnapshot = config.appVersion.indexOf("-SNAPSHOT") > -1;
  var repoId = isSnapshot ? "conduittest-snapshot-repo" : "conduittest-release-repo";
  maven.deploy(repoId, isSnapshot);
});
