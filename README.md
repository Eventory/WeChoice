# WeChoice

微信精选 App 源码，24小时不间断精选微信优质文章。

MVP模式 + Material Design。

![](https://github.com/fuxuemingzhu/fuxuemingzhu.github.io/blob/master/images/WeChoice/ic_launcher_144.png?raw=true)

下载地址：

[http://fir.im/b4zp](http://fir.im/b4zp)


##　Screenshot

![](https://github.com/fuxuemingzhu/fuxuemingzhu.github.io/blob/master/images/WeChoice/%E5%B1%95%E7%A4%BA1.png?raw=true)

![](https://github.com/fuxuemingzhu/fuxuemingzhu.github.io/blob/master/images/WeChoice/%E5%B1%95%E7%A4%BA2.png?raw=true)

## Fuctions

1. 每日精选微信优质文章
2. 每次下拉刷新都会获取不同列表
3. 查看文章详细内容
4. 主界面采用EasyRecyclerView+Adatper
5. 详情页面采用WebView
6. 项目整体采用MVP模式

## Dependencies

- [appcompat-v7](https://developer.android.com/tools/support-library/features.html#v7-appcompat)/[cardview-v7](https://developer.android.com/tools/support-library/features.html#v7-cardview)
- [Beam](https://github.com/Jude95/Beam)
- [ButterKnife](http://jakewharton.github.io/butterknife/)
- [requestvolley](https://github.com/Jude95/RequestVolley)
- [utils](https://github.com/Jude95/Utils)
- [fastjson](https://github.com/alibaba/fastjson)
- [glide](https://github.com/bumptech/glide)

## API

[http://v.juhe.cn/weixin/query](http://v.juhe.cn/weixin/query)

说明：如果想基于此项目继续开发，请务必自己在聚合数据上申请微信精选的key添加到 /model/ChoiceModel.java 中。

## Licence

**所有修改必须开源，且必须遵循相同协议，如有商用计划务必联系本作者。**

**本作者保留一切商业权利。**

> Copyright 2016 fuxuemingzhu
> 
> This program is free software: you can redistribute it and/or modify
> it under the terms of the GNU General Public License as published by
> the Free Software Foundation, either version 3 of the License, or
> (at your option) any later version.
> 
> This program is distributed in the hope that it will be useful,
> but WITHOUT ANY WARRANTY; without even the implied warranty of
> MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
> GNU General Public License for more details.
> 
> You should have received a copy of the GNU General Public License
> along with this program.  If not, see <http://www.gnu.org/licenses/>.