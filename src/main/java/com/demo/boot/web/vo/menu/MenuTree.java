package com.demo.boot.web.vo.menu;

import java.util.ArrayList;
import java.util.List;

public class MenuTree {

    static List<MenuNode> nodes = new ArrayList<>();

    public MenuTree(List<MenuNode> nodes) {
        super();
        this.nodes = nodes;
    }

    /**
     * 判断是否跟节点
     *
     * @param node
     * @return
     */
    boolean isRoot(MenuNode node) {
        for (MenuNode n : nodes) {
            if (n.getId().equals(node.getParentId())) {
                return false;
            }
        }
        return true;
    }

    /**
     * 获取所有根节点
     *
     * @return
     */
    List<MenuNode> getRoots() {
        List<MenuNode> roots = new ArrayList<>();
        for (MenuNode n : MenuTree.nodes) {
            if (isRoot(n)) {
                n.setLevel(1);
                roots.add(n);
            }
        }
        return roots;
    }

    /**
     * 构造Tree
     *
     * @return
     */
    public List<MenuNode> buildTree() {
        List<MenuNode> treeNodes = new ArrayList<>();
        List<MenuNode> rootNodes = getRoots();
        for (MenuNode rootNode : rootNodes) {
            buildChildNodes(rootNode);
            treeNodes.add(rootNode);
        }
        return treeNodes;
    }

    /**
     * 增加子节点
     *
     * @param node
     */
    void buildChildNodes(MenuNode node) {
        List<MenuNode> children = getChildNodes(node);
        if (!children.isEmpty()) {
            for (MenuNode child : children) {
                buildChildNodes(child);
            }
            node.setNodes(children);
        }
    }

    /**
     * 根据上一级获取子节点
     *
     * @param node
     * @return
     */
    List<MenuNode> getChildNodes(MenuNode node) {
        List<MenuNode> children = new ArrayList<>();
        for (MenuNode n : nodes) {
            if (n.getParentId().equals(node.getId())) {
                n.setLevel(node.getLevel() + 1);
                children.add(n);
            }
        }
        return children;
    }
}
