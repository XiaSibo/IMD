# Introduce the Flutter

<strong>&emsp;&emsp;夏思博&emsp;&emsp;1813035</strong>

## 1、Flutter 面世
- Flutter 是一款 Google 在 2015 年推出的移动 UI 框架、应用程序 SDK ，实现一份代码可以同时生成 iOS 和 Android 两个高性能、高保真的应用程序，可快速在 iOS 和 Android 上构建高质量的原生用户界面。Flutter 第一次亮相于 2015 年 5 月 Dart 开发者峰会上，初始名为" Sky "，后更名为" Flutter "，Flutter 使用 Dart 语言开发（ Dart 是 Google 于 2011 年推出的新的计算机编程语言）
- Flutter 的目标是使开发人员能够交付在不同平台上都感觉自然流畅的高性能应用程序，兼容滚动行为、排版、图标等方面的差异
- Flutter 包括一个现代的响应式框架、一个 2D 渲染引擎、现成的 widget 和开发工具，这些组件可以快速地设计、构建、测试和调试应用程序
  
## 2、Flutter 特点
### I. 提高开发效率
- 同一份代码开发 iOS 和 Android
- 用更少的代码做更多的事情
- 在应用程序运行时更改代码并重新加载
- 修复崩溃并继续从应用程序停止的地方进行调试
### II. 性能优越
- 使用自带的高性能渲染引擎（ Skia ）进行自绘，渲染速度和用户体验堪比原生
### III. 创造美观、高度定制的用户体验
- Flutter 框架内置丰富的 Android Material 风格和 iOS Cupertino 风格的 widgets 小部件
- 实现定制、美观、品牌驱动的设计，而不受原生控件的限制，开发者可快速构建精美的用户界面，以提供更好的用户体验

## 3、Flutter 框架
### Flutter 框架分为三层：Framework、Engine、Embedder
- Framework 层：由 Dart 实现，包含众多 iOS 风格和 Android 风格的 widgets 小部件，还有渲染、动画、绘图和手势等。其中包含了日常开发所需要的的大量 API
- Engine 层：由 C / C++ 实现，是 Flutter 的核心引擎，主要包括 Skia 图形引擎、Dart 运行时环境 Dart VM 、Text 文本渲染引擎等
- Embedder 层：主要处理一些平台相关的操作，包括渲染 Surface 设置、本地插件、打包、线程设置等

## 4、Flutter 核心原则
### 一切皆为 widget
- Widget 是 Flutter 应用程序用户界面的基本构建块
- 每个 Widget 都是用户界面一部分的不可变声明
- 与其他将视图、控制器、布局和其他属性分离的框架不同，Flutter具有一致的统一对象模型 --- Widget
  
## 5、Flutter 原理
### Flutter 图形渲染流程：
- GPU 的 Vsync 信号同步到 UI 线程
- UI 线程使用 Dart 来构建抽象的视图结构
- 视图结构在 GPU 线程中进行图层合成
- 合成后的视图数据提供给 Skia 图形引擎处理成 GPU 数据
- 数据在通过 OpenGL 或 Vulkan 提供给 GPU 进行渲染