package wechat.core.entity.api;

import java.util.List;

public class CreateMenu {

    private List<Button> button;

    public List<Button> getButton() {
        return button;
    }

    public void setButton(List<Button> button) {
        this.button = button;
    }

    public static class Button {

        private String name;
        private String type;
        private String key;
        private String url;
        private String appid;
        private String pagepath;
        private String media_id;
        private List<Button> sub_button;

        public String getName() {
            return name;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getAppid() {
            return appid;
        }

        public void setAppid(String appid) {
            this.appid = appid;
        }

        public String getPagepath() {
            return pagepath;
        }

        public void setPagepath(String pagepath) {
            this.pagepath = pagepath;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public String getMedia_id() {
            return media_id;
        }

        public void setMedia_id(String media_id) {
            this.media_id = media_id;
        }

        public List<Button> getSub_button() {
            return sub_button;
        }

        public void setSub_button(List<Button> sub_button) {
            this.sub_button = sub_button;
        }

    }
}
