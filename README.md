# SVGCinaMap
SVG 绘制可交互的中国地图

![image](https://github.com/yangxiansheng123/SVGCinaMap/blob/master/20180808173616.gif)


DocumentBuilderFactory解析XML：

(1) javax.xml.parsers 包中的DocumentBuilderFactory用于创建DOM模式的解析器对象 ， DocumentBuilderFactory是一个抽象工厂类，它不能直接实例化，但该类提供了一个newInstance方法 ，这个方法会根据本地平台默认安装的解析器，自动创建一个工厂的对象并返回。

(2) 调用 DocumentBuilderFactory.newInstance() 方法得到创建 DOM 解析器的工厂。

    DocumentBuilderFactory doc=DocumentBuilderFactory.newInstance();

(3) 调用工厂对象的 newDocumentBuilder方法得到 DOM 解析器对象。

     DocumentBuilder db=doc.newDocumentBuilder();

(4) 把要解析的 XML 文档转化为输入流，以便 DOM 解析器解析它

    InputStream inputStream = context.getResources().openRawResource(R.raw.china);
    
(5) 调用 DOM 解析器对象的 parse() 方法解析 XML 文档，得到代表整个文档的 Document 对象，进行可以利用DOM特性对整个XML文档进行操作了。

     Document doc = builder.parse(inputStream);

(6) 得到 XML 文档的根节点

   Element rootElement = doc.getDocumentElement();
   
(7) 获取子节点

    NodeList items = rootElement.getElementsByTagName("path");
    
 (8) 获取属性
 
    String pathData = element.getAttribute("android:pathData");
    String proviceData = element.getAttribute("android:provice");
