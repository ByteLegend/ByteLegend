# 字节传说：玩游戏，学编程

[![check](https://github.com/ByteLegend/ByteLegend/actions/workflows/check.yml/badge.svg)](https://github.com/ByteLegend/ByteLegend/actions/workflows/check.yml)

## 字节传说是什么

[字节传说](https://bytelegend.com)是一个免费、开源的多人在线RPG游戏，通过这个游戏，你可以学到现实世界里的编程技能。

![1](https://raw.githubusercontent.com/ByteLegend/ByteLegend/master/docs/images/index-page-zh.png)

## 如何玩

- 访问[字节传说](https://bytelegend.com)，点击右上角的登录按钮并使用GitHub账号登录。
- 和其他游戏一样，玩就是了——和NPC对话，收集物品，完成任务，等等。
- 和其他游戏不同的是，你在这个游戏中获得的是真实世界里的高薪编程技能。

## 为什么选择字节传说

- 免费
  - 是的，我们是免费的。
- 开源
  - 是的，我们是开源的。这意味着你在游戏里看到的任何东西都是可以修改的：游戏地图、流程、NPC对话、国际化的文本，等等。
  - 如果您在游戏中看到任何不对的地方，请不要犹豫直接提交issues/pull requests。我们和成千上万的玩家一起感谢您的贡献。
  - 点击[这里](https://github.com/ByteLegend/ByteLegend/blob/master/docs/zh_hans/CONTRIBUTING.md)查看如何贡献代码。
- 好玩
  - 因为这是一个游戏，不是啰里八嗦的说教。
- 特别为中国玩家优化的服务器
  - 我们有ICP备案，因此特别在国内部署了服务器和CDN。
  - 您不需要做任何配置，我们通过基于地理位置的DNS服务自动为您分配国内服务器和CDN。
- 国际化
  - 从第一天起，我们就支持地球上的**所有语言**，您只需要点击[字节传说](https://bytelegend.com)右上角的切换语言按钮即可。
  - 如前所述，你可以对[字节传说](https://bytelegend.com)中能看到的任何东西做出修改，包括修正不佳的翻译或者添加一种新语言。
  - 点击[这里](https://github.com/ByteLegend/ByteLegend/blob/master/docs/zh_hans/i18n.md)查看如何增加一种语言或者帮助我们改进翻译的质量。  

## 联系我们

您可以在[Discord](https://discord.gg/PvmqK3hF)上加入我们或者通过email联系： [contact@bytelegend.com](mailto:contact@bytelegend.com)。

## 加入玩家QQ群

我们的玩家QQ群是`788942934`，但是请注意您需要在登录游戏之后打开右下角的`关于&联系`菜单获取入群密码后方可加入。入群密码各不相同，且只能
使用一次，请勿泄漏给他人。

![1](https://raw.githubusercontent.com/ByteLegend/ByteLegend/master/docs/images/qq-group.png)

## 致谢

没有社区和开源世界的帮助，这个游戏不可能存在。我们唯一能做的就是让它免费开源，从而使更多人受益。

请访问[字节传说](https://bytelegend.com)并点击右下角的`致谢`按钮查看本游戏使用的开源项目和游戏资源。

## 贡献

我们感谢您作出的任何贡献：

- 任何文字错误：typo、用词或者翻译不当。
- 游戏地图错误：有问题的格子图片、错误的标注等。
- Bugs：前端、后端以及游戏脚本。

详情请阅读[贡献者指南](https://github.com/ByteLegend/ByteLegend/blob/master/docs/zh_hans/CONTRIBUTING.md)。

## 本地启动

请确保你的`JAVA_HOME`指向JDK 11。

- `git clone https://github.com/ByteLegend/ByteLegend`
- `cd ByteLegend`
- `git submodule update --init -- game-data`
- 运行`./gradlew bootRun`，然后访问`http://localhost:8080`

第一次运行可能要花费几分钟，因为我们需要生成所有的游戏资源。后续的启动应该只花几秒钟。

请注意`server` git submodule目前尚未开源，因为其中包含复杂的后端配置和敏感信息（GitHub webhook, GitHub app, k8s secrets等）。
不过，我们在`server-opensource`中提供了一个开源版本的后端服务实现。

点击[这里](https://github.com/ByteLegend/ByteLegend/blob/master/docs/zh_hans/game-code-contributor-guide.md) 查看开发者文档。
