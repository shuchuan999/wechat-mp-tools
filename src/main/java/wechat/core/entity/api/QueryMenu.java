package wechat.core.entity.api;

import java.util.List;

public class QueryMenu {

    private int is_menu_open;
    private MenuInfo selfmenu_info;

    public int getIs_menu_open() {
        return is_menu_open;
    }

    public void setIs_menu_open(int is_menu_open) {
        this.is_menu_open = is_menu_open;
    }

    public MenuInfo getSelfmenu_info() {
        return selfmenu_info;
    }

    public void setSelfmenu_info(MenuInfo selfmenu_info) {
        this.selfmenu_info = selfmenu_info;
    }

    public static class MenuInfo {
        private List<Button> button;

        public List<Button> getButton() {
            return button;
        }

        public void setButton(List<Button> button) {
            this.button = button;
        }

        public static class Button {

            private NewsInfoList news_info;
            private String name;
            private SubButtonList sub_button;
            private String type;
            private String value;
            private String key;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public SubButtonList getSub_button() {
                return sub_button;
            }

            public void setSub_button(SubButtonList sub_button) {
                this.sub_button = sub_button;
            }

            public String getKey() {
                return key;
            }

            public void setKey(String key) {
                this.key = key;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getValue() {
                return value;
            }

            public void setValue(String value) {
                this.value = value;
            }

            public static class NewsInfoList {
                private List<NewsInfo> list;

                public List<NewsInfo> getList() {
                    return list;
                }

                public void setList(List<NewsInfo> list) {
                    this.list = list;
                }

                public static class NewsInfo {
                    private String title;
                    private String author;
                    private String digest;
                    private int show_cover;
                    private String cover_url;
                    private String content_url;
                    private String source_url;

                    public String getTitle() {
                        return title;
                    }

                    public void setTitle(String title) {
                        this.title = title;
                    }

                    public String getAuthor() {
                        return author;
                    }

                    public void setAuthor(String author) {
                        this.author = author;
                    }

                    public String getDigest() {
                        return digest;
                    }

                    public void setDigest(String digest) {
                        this.digest = digest;
                    }

                    public int getShow_cover() {
                        return show_cover;
                    }

                    public void setShow_cover(int show_cover) {
                        this.show_cover = show_cover;
                    }

                    public String getCover_url() {
                        return cover_url;
                    }

                    public void setCover_url(String cover_url) {
                        this.cover_url = cover_url;
                    }

                    public String getContent_url() {
                        return content_url;
                    }

                    public void setContent_url(String content_url) {
                        this.content_url = content_url;
                    }

                    public String getSource_url() {
                        return source_url;
                    }

                    public void setSource_url(String source_url) {
                        this.source_url = source_url;
                    }
                }
            }

            public static class SubButtonList {
                private List<Button> list;

                public List<Button> getList() {
                    return list;
                }

                public void setList(List<Button> list) {
                    this.list = list;
                }

            }
        }
    }
}
