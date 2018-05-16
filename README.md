# netease sms sdk

### installation
```
repositories {
    mavenCentral()
    maven { url 'https://jitpack.io' }
}

dependencies {
    compile("com.github.noob9527:netease-sms-sdk:master-SNAPSHOT")
    // ...
}
```
### getting started
if you are using spring boot, related service are autoconfigure, otherwise you have to create them on your own

### configuration
| key | required | default |
| - | - | - |
| netease.im.sms.app-key                    | true ||
| netease.im.sms.app-secret                 | true ||
| netease.im.sms.default-code-template-id   | false ||
| netease.im.sms.log-level                  | true | NONE |

NOTE: if you want to call sendCode method without passing a templateId parameter, then default-code-template-id is required

