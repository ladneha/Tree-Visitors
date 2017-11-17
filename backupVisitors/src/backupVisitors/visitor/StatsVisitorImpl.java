package backupVisitors.visitor;

import java.util.ArrayList;



import java.util.Collections;

import backupVisitors.myTree.Node;
import backupVisitors.util.Results;
import backupVisitors.util.TreeBuilder;

public class StatsVisitorImpl implements TreeVisitorI{

	ArrayList<Integer> stat = new ArrayList<Integer>();
	int i;
	float average;
	
	@Override
	public void visit(TreeBuilder tree, Results result) {
		// TODO Auto-generated method stub
		float sum = 0;
		double median=0;
		 check(tree.root.getObservers().get(0), result);
		 for(i=0;i<stat.size();i++){
			 //System.out.println(stat.get(i));
		 }
		 
		 //finding the average
		 for(i=0;i<stat.size();i++){
		   sum = sum + stat.get(i);	 
		 }
		 average = sum/(float)stat.size();
		 result.writeToFile("Average is: "+average);
		 
		 //finding the median
		Collections.sort(stat);
		if(stat.size()%2 == 0)
		{
			median = stat.get(stat.size()/2) + stat.get(stat.size()/2 - 1);
			result.writeToFile("Median is : "+median/2);
		}
		else{
			median = stat.get(stat.size()/2);
			result.writeToFile("Median is : "+median);
		}
	}
	
	public void check(Node root, Results result){
		int value = 0;
		if(root!=null){
			check(root.left, result);
			
			if(root.courses.contains("S"))
			{
				value = root.courses.size();
				value--;
				stat.add(value);
			}
			else{
				value = root.courses.size();
				stat.add(value);
			}
			check(root.right, result);
		}
		else{
	//		System.out.println("List is Empty");
		}
	}

	@Override
	public void visit(TreeBuilder tree) {
		// TODO Auto-generated method stub
		
	}

}
