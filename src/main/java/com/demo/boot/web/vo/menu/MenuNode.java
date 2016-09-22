package com.demo.boot.web.vo.menu;

import com.demo.boot.entity.Permission;

import java.util.List;

public class MenuNode {

    private String id;
    private String name;
    private String parentId;
    private String link;
    private String target;
    private int level;
    private List<MenuNode> nodes;

    public MenuNode(Permission m) {
        this.id = m.getId();
        this.name = m.getName();
        this.parentId = m.getParentId();
        this.link = m.getLink();
        this.target = m.getTarget();
    }

    /**
     * 判断是否有子节点
     *
     * @return
     */
    public boolean hasNode() {
        return nodes != null && !nodes.isEmpty();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public List<MenuNode> getNodes() {
        return nodes;
    }

    public void setNodes(List<MenuNode> nodes) {
        this.nodes = nodes;
    }
}
