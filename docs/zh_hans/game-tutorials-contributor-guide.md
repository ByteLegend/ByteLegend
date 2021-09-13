# 游戏教程贡献者指南

进入**游戏挑战页面**，切换到**教程**选项卡，点击**贡献更好的教程**。如果提示 fork 项目，请点击 fork。

在`missions`的同级目录中打开`docs`目录（如果没有请创建），在其中创建与`YAML`文件名相同的目录。

![Same name folder with YAML file](https://raw.githubusercontent.com/ByteLegend/ByteLegend/master/docs/images/same-name-folder-with-yaml-file.png)

在新创建的目录中创建**对应的教程文档**，教程文档后请加入**语言代码**。图片文件请放入与文件名对应的目录中，如果为各语言通用图片，可不加入**语言代码**。

⚠️ 注意：图片 URL 建议设置为对应的`https://raw.githubusercontent.com`链接。

![Create tutorials](https://raw.githubusercontent.com/ByteLegend/ByteLegend/master/docs/images/create-tutorials.png)

返回`YAML`文件，在`tutorials`节点中添加新的**教程**。

![Add tutorials to YAML file](https://raw.githubusercontent.com/ByteLegend/ByteLegend/master/docs/images/add-tutorials-to-yaml-file.png)

下面列举几个已有的**类型**（type）：

| 文本           | 对应值        |
| -------------- | ------------- |
| text/markdown  | Markdown 文本 |
| video/youtube  | YouTube 视频  |
| video/bilibili | Bilibili 视频 |
