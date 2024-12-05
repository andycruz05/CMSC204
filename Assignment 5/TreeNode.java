// @author Andy Cruz

public class TreeNode<T> {
	private T data;
	protected TreeNode<T>leftChild;
	protected TreeNode<T>rightChild;
	
	/**
	 * @param dataNode - data to be stored in the tree node
	 */
	
	public TreeNode(T dataNode) {
		data=dataNode;
		leftChild=null;
		rightChild=null;
	}
	
	/**
	 * @param node node to be copied
	 */
	
	public TreeNode(TreeNode<T> node) {
		this(node.leftChild,node.rightChild,node.getData());
	}
	
	/**
	 * @param left left child
	 * @param right right child
	 * @param info data stores in the node
	 */
	
	public TreeNode(TreeNode<T> left,TreeNode<T> right,T info) {		
		data= info;
		leftChild=new TreeNode<T>(left);
		rightChild=new TreeNode<T>(right);
	}
	
	/**
	 * @return data
	 */
	
	public T getData() {
		return data;
	}

}