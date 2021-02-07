/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.baldaccini.bkpsgbweb.modelo;

import javax.swing.event.TreeModelListener;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;

/**
 *
 * @author baldaccini
 */
public class FileTreeModel implements TreeModel {

    // We specify the root directory when we create the model.
    // Nós especificar o diretório raiz quando criamos o modelo.
    protected TipoArqPasta root;

    public FileTreeModel(TipoArqPasta root) {
        this.root = root;
    }

    // The model knows how to return the root object of the tree
    // O modelo sabe como retornar o objeto raiz da árvore
    public Object getRoot() {
        return root;
    }

    // Tell JTree whether an object in the tree is a leaf
    // Diz JTree se um objeto na árvore é uma folha(Arquivo ou pasta)
    public boolean isLeaf(Object node) {
        return ((TipoArqPasta) node).isFile();
    }

    // Tell JTree how many children a node has
    // Diga JTree quantos filhos tem um nó
    public int getChildCount(Object parent) {
        String[] children = ((TipoArqPasta) parent).list();
        if (children == null) {
            return 0;
        }
        return children.length;
    }

    // Fetch any numbered child of a node for the JTree.
    // Our model returns File objects for all nodes in the tree.  The
    // JTree displays these by calling the File.toString() method.
    // Fetch qualquer criança numerados de um nó para o JTree.
    // Os nossos retornos modelo Objetos do arquivo para todos nós na árvore. o
    // JTree exibe estes chamando o método File.toString ().
    public Object getChild(Object parent, int index) {
        String[] children = ((TipoArqPasta) parent).list();
        if ((children == null) || (index >= children.length)) {
            return null;
        }

        return new TipoArqPasta((TipoArqPasta) parent, children[index]);
    }

    // Figure out a child's position in its parent node.
    //Descobrir a posição da FILHA em seu nó PAI
    public int getIndexOfChild(Object parent, Object child) {
        String[] children = ((TipoArqPasta) parent).list();
        if (children == null) {
            return -1;
        }
        String childname = ((TipoArqPasta) child).getName();
        for (int i = 0; i < children.length; i++) {
            if (childname.equals(children[i])) {
                return i;
            }
        }
        return -1;
    }

    // This method is invoked by the JTree only for editable trees.  
    // This TreeModel does not allow editing, so we do not implement 
    // this method.  The JTree editable property is false by default.
    public void valueForPathChanged(TreePath path, Object newvalue) {
    }

    // Since this is not an editable tree model, we never fire any events,
    // so we don't actually have to keep track of interested listeners
    public void addTreeModelListener(TreeModelListener l) {
    }

    public void removeTreeModelListener(TreeModelListener l) {
    }
}
