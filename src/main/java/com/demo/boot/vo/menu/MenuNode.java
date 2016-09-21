package com.demo.boot.vo.menu;

import com.demo.boot.entity.Permission;

import java.util.List;

public class MenuNode {

    private Integer id;
    private String name;
    private Integer parentId;
    private String link;
    private int level;
    private List<MenuNode> nodes;

    public MenuNode(Permission m) {
        this.id = m.getId();
        this.name = m.getName();
        this.parentId = m.getParentId();
        this.link = m.getLink();
    }

    /**
     * 判断是否有子节点
     *
     * @return
     */
    public boolean hasNode() {
        return nodes != null && !nodes.isEmpty();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
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
