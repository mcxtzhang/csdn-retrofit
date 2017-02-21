package anlaiye.com.cn.csdn_retrofit.normal;

import java.util.List;

/**
 * 介绍：
 * 作者：zhangxutong
 * 邮箱：mcxtzhang@163.com
 * 主页：http://blog.csdn.net/zxt0601
 * 时间： 2017/2/21.
 */
public class GetBean {
    @Override
    public String toString() {
        return "GetBean{" +
                "error=" + error +
                ", results=" + results +
                '}';
    }

    /**
     * error : false
     * results : [{"_id":"58aa8ade421aa93d3a0a866d","createdAt":"2017-02-20T14:21:18.815Z","desc":"探秘Android消息机制（萌新的角度看源码）","publishedAt":"2017-02-21T11:14:09.564Z","source":"web","type":"Android","url":"https://zhuanlan.zhihu.com/p/25222485?refer=levent-j","used":true,"who":"Li Wenjing"},{"_id":"58ab81c7421aa93d3d15aa3e","createdAt":"2017-02-21T07:54:47.295Z","desc":"一款漂亮的每周日历组件。","images":["http://img.gank.io/a708c59d-7949-4ac0-855d-c46a2d1825b2"],"publishedAt":"2017-02-21T11:14:09.564Z","source":"chrome","type":"Android","url":"https://github.com/nomanr/weekcalendar","used":true,"who":"代码家"},{"_id":"58ab9732421aa93d376f74f1","createdAt":"2017-02-21T09:26:10.367Z","desc":"A mini and excellent Router Framwork一款小而美的路由框架。网页动态添加自定义参数启动应用。","images":["http://img.gank.io/fe97f318-5621-417a-9e83-0abc11ff3127"],"publishedAt":"2017-02-21T11:14:09.564Z","source":"web","type":"Android","url":"https://github.com/Jomes/routerSDK","used":true,"who":"Jomeslu"},{"_id":"58ab9fdd421aa93d376f74f3","createdAt":"2017-02-21T10:03:09.90Z","desc":"比原生 Snack 更漂亮的 Bottom Notification 库。","images":["http://img.gank.io/67940797-a360-499c-9972-88af31aeba37","http://img.gank.io/23c7eeca-1ea4-4e92-a521-90610d3a3fc1"],"publishedAt":"2017-02-21T11:14:09.564Z","source":"chrome","type":"Android","url":"https://github.com/matecode/Snacky","used":true,"who":"代码家"},{"_id":"58aba04e421aa93d3a0a8673","createdAt":"2017-02-21T10:05:02.451Z","desc":"Android HTML5，LaTeX 转换器，而且支持自定义标签。","images":["http://img.gank.io/5362b013-e9ed-4eba-85ce-e3fd50cbb3fa"],"publishedAt":"2017-02-21T11:14:09.564Z","source":"chrome","type":"Android","url":"https://github.com/daquexian/FlexibleRichTextView","used":true,"who":"代码家"},{"_id":"58aba446421aa93d3d15aa44","createdAt":"2017-02-21T10:21:58.551Z","desc":"SMS 验证小工具，自动帮你读取短信，然后填写短信验证码，中国同胞们可以贡献些规则上去。","images":["http://img.gank.io/442b635f-a7f4-4bbf-ba18-ecfaedec0ca5"],"publishedAt":"2017-02-21T11:14:09.564Z","source":"chrome","type":"Android","url":"https://github.com/stfalcon-studio/SmsVerifyCatcher","used":true,"who":"代码家"},{"_id":"58aba7a3421aa93d3d15aa45","createdAt":"2017-02-21T10:36:19.921Z","desc":"二维码扫描的封装库","publishedAt":"2017-02-21T11:14:09.564Z","source":"web","type":"Android","url":"https://github.com/XuDaojie/QRCode-Android","used":true,"who":null},{"_id":"58aa6681421aa93d376f74e9","createdAt":"2017-02-20T11:46:09.793Z","desc":"Android 富文本（HTML）解析库，渲染的非常漂亮，值得推荐。","images":["http://img.gank.io/6c6660ab-4cd7-4ecb-b0b3-72d49c5a8cf8"],"publishedAt":"2017-02-20T11:56:22.616Z","source":"chrome","type":"Android","url":"https://github.com/fourlastor/dante","used":true,"who":"daimajia"},{"_id":"58aa66b4421aa93d3d15aa33","createdAt":"2017-02-20T11:47:00.216Z","desc":"很有新意的一个滑动效果库。","images":["http://img.gank.io/b2efeade-98d3-40e5-8cc2-9d06738a8e3e"],"publishedAt":"2017-02-20T11:56:22.616Z","source":"chrome","type":"Android","url":"https://github.com/MAXDeliveryNG/slideview","used":true,"who":"XM"},{"_id":"58aa6745421aa93d33938855","createdAt":"2017-02-20T11:49:25.96Z","desc":"双指控制图片展开，以及放大缩小，做的超棒！","images":["http://img.gank.io/e3a64868-35b2-46e0-86c4-4ca6e42c2f12"],"publishedAt":"2017-02-20T11:56:22.616Z","source":"chrome","type":"Android","url":"https://github.com/imablanco/Zoomy","used":true,"who":"代码家"}]
     */

    private boolean error;
    private List<ResultsBean> results;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public List<ResultsBean> getResults() {
        return results;
    }

    public void setResults(List<ResultsBean> results) {
        this.results = results;
    }

    public static class ResultsBean {
        @Override
        public String toString() {
            return "ResultsBean{" +
                    "_id='" + _id + '\'' +
                    ", createdAt='" + createdAt + '\'' +
                    ", desc='" + desc + '\'' +
                    ", publishedAt='" + publishedAt + '\'' +
                    ", source='" + source + '\'' +
                    ", type='" + type + '\'' +
                    ", url='" + url + '\'' +
                    ", used=" + used +
                    ", who='" + who + '\'' +
                    ", images=" + images +
                    '}';
        }

        /**
         * _id : 58aa8ade421aa93d3a0a866d
         * createdAt : 2017-02-20T14:21:18.815Z
         * desc : 探秘Android消息机制（萌新的角度看源码）
         * publishedAt : 2017-02-21T11:14:09.564Z
         * source : web
         * type : Android
         * url : https://zhuanlan.zhihu.com/p/25222485?refer=levent-j
         * used : true
         * who : Li Wenjing
         * images : ["http://img.gank.io/a708c59d-7949-4ac0-855d-c46a2d1825b2"]
         */

        private String _id;
        private String createdAt;
        private String desc;
        private String publishedAt;
        private String source;
        private String type;
        private String url;
        private boolean used;
        private String who;
        private List<String> images;

        public String get_id() {
            return _id;
        }

        public void set_id(String _id) {
            this._id = _id;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public String getPublishedAt() {
            return publishedAt;
        }

        public void setPublishedAt(String publishedAt) {
            this.publishedAt = publishedAt;
        }

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public boolean isUsed() {
            return used;
        }

        public void setUsed(boolean used) {
            this.used = used;
        }

        public String getWho() {
            return who;
        }

        public void setWho(String who) {
            this.who = who;
        }

        public List<String> getImages() {
            return images;
        }

        public void setImages(List<String> images) {
            this.images = images;
        }
    }
}
