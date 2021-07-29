package wechat.core.entity.api;

public class DeleteTemplate {
    private String template_id;

    public DeleteTemplate(String template_id) {
        this.template_id = template_id;
    }

    public DeleteTemplate() {
    }

    public String getTemplate_id() {
        return template_id;
    }

    public void setTemplate_id(String template_id) {
        this.template_id = template_id;
    }
}
