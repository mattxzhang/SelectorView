# SelectorView
Implemention of selector when pressed by one drawable on view.
## Usage
Add the dependencies to your gradle file:
```javascript
dependencies {
    compile 'com.chrischeng:selectorview:1.0.1'
}
```
**XML:**
```xml
<com.chrischeng.selectorview.SelectorImageView
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        app:siv_filter_color="0xffcccccc"/> //default
```
**Java:**
```java
selectorImageView.setFilterColor(Color.GRAY);
```
## License
    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
