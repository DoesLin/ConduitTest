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
  "groupId": "net.atos.m2m",
  "artifactId": config.appName,
  "version": config.appVersion,
  "buildDir": config.appDir,
  "finalName": config.finalZipName,
  "type": "zip",
  "fileEncoding": "utf-8",
  "repositories": [{
    "id": "kazan-release-repo",
    "url": "http://kazan.priv.atos.fr/nexus/content/repositories/releases"
  }, {
    "id": "kazan-snapshot-repo",
    "url": "http://kazan.priv.atos.fr/nexus/content/repositories/snapshots"
  }]
};
maven.config(mavenConfig);
gulp.task('install', function() {
  maven.install();
});
gulp.task('deploy', function() {
  var isSnapshot = config.appVersion.indexOf("-SNAPSHOT") > -1;
  var repoId = isSnapshot ? "kazan-snapshot-repo" : "kazan-release-repo";
  maven.deploy(repoId, isSnapshot);
});
