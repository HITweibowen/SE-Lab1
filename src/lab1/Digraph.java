package lab1;

import java.util.Vector;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import lab1.GUI.Input;

import java.util.HashMap;
import java.util.Map;
import java.io.IOException;
import java.io.Reader;
import java.io.InputStreamReader;
import java.io.File;
import java.io.FileInputStream;
import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.Comparator;

public class Digraph {
    private Vector<DigraphNode> headNodeList;
    public Vector<String> nodeList;
    public Vector<String> fileText;
    private String fileLastString;
    public Vector<String> BridgeWords;
    private Map<String, Boolean> visit;
    public Vector<String> KeyNode = new Vector<>();
    public Vector<String> shortPath = new Vector<>();
    public Vector<PQueue> nodeShortPath = new Vector<>();

    public Digraph() {
        headNodeList = new Vector<>();
        nodeList = new Vector<>();
        fileText = new Vector<>();
        BridgeWords = new Vector<>();
        visit = new HashMap<>();
    }

    public void insert(String curNodeKey, String posNodeKey) {
        DigraphNode NewNode = new DigraphNode();
        NewNode.Words = posNodeKey;
        int i = 0;
        int Size = headNodeList.size();
        for (; i < Size; i++) {
            if (curNodeKey.equals(headNodeList.get(i).Words))//遍历表头节点,单词连续重复的情况
            {
                DigraphNode Node = headNodeList.get(i);

                while (Node != null) {
                    if (Node.Words.equals(posNodeKey)) {
                        Node.Weight++;
                        break;
                    }
                    Node = Node.Next;
                }
                if (Node == null) {
                    NewNode.Next = (headNodeList.get(i)).Next;
                    NewNode.Weight = 1;
                    (headNodeList.get(i)).Next = NewNode;
                    (headNodeList.get(i)).AdjPointNumber++;
                }
                break;
            }
        }
        if (i == Size)//出现新的表头节点
        {
            DigraphNode HeadNode = new DigraphNode();
            HeadNode.Next = NewNode;
            HeadNode.Words = curNodeKey;
            HeadNode.AdjPointNumber++;
            NewNode.Next = null;
            NewNode.Weight = 1;
            headNodeList.addElement(HeadNode);
        }
    }

    public void readFileBuildDigraph(String fileName) {
        File file = new File(fileName);
        try (
                Reader reader = new InputStreamReader(new FileInputStream(file))) {
            int tempchar;
            int CurFlag = 1;
            StringBuffer CurNodeKey = new StringBuffer("");
            StringBuffer PosNodeKey = new StringBuffer("");
            while ((tempchar = reader.read()) != -1) {
                if (tempchar != '\r') {
                    int x = changeWords(CurFlag, CurNodeKey, PosNodeKey, tempchar);

                    CurFlag = x;
                }
            }


            if (!PosNodeKey.toString().equals("")) {
                insert(CurNodeKey.toString(), PosNodeKey.toString());
                fileText.addElement(CurNodeKey.toString());//
                fileText.addElement(PosNodeKey.toString());//
                if (!nodeList.contains(CurNodeKey.toString())) {
                    nodeList.addElement(CurNodeKey.toString());//
                }
                if (!nodeList.contains(PosNodeKey.toString())) {
                    nodeList.addElement(PosNodeKey.toString());//
                }
            } else {
                fileText.addElement(fileLastString);//
                if (!nodeList.contains(fileLastString)) {
                    visit.put(fileLastString, false);
                    nodeList.addElement(fileLastString);
                }//
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

public class Digraph
{
	private Vector<DigraphNode> HeadNodeList;
	public Vector<String> NodeList;
	public Vector<String> FileText;
	private Map<String, Boolean> Visit;
    private String FileLastString;
    public Vector<String>BridgeWords;
    public Vector<PQueue> NodeShortPath = new Vector<PQueue>();
    public Vector<String> KeyNode = new Vector<String>();
    public Vector<String> ShortPath = new Vector<String>();


	public Digraph()
	{
		HeadNodeList = new Vector<DigraphNode >();
		NodeList = new Vector<String>();
		FileText = new Vector<String>();
		BridgeWords = new Vector<String>();
		Visit = new HashMap<String, Boolean>();

	}

	public void Insert(String CurNodeKey, String PosNodeKey)
	{
		DigraphNode NewNode = new DigraphNode();
		NewNode.Words = PosNodeKey;
		int i = 0;
		int Size = HeadNodeList.size();
		for (; i < Size; i++)
		{
			if (CurNodeKey.equals (HeadNodeList.get(i).Words))//������ͷ�ڵ�,���������ظ������
			{
				DigraphNode Node = HeadNodeList.get(i);

				while (Node != null)
				{
					if (Node.Words.equals(PosNodeKey))
					{
						Node.Weight++;
					    break;
					}
					Node = Node.Next;
				}
				if (Node == null)
				{
					NewNode.Next = (HeadNodeList.get(i)).Next;
					NewNode.Weight = 1;
					(HeadNodeList.get(i)).Next = NewNode;
					(HeadNodeList.get(i)).AdjPointNumber++;
				}
				break;
			}
		}
		if (i == Size)//�����µı�ͷ�ڵ�
		{
			DigraphNode HeadNode = new DigraphNode();
			HeadNode.Next = HeadNode.Next = NewNode;//��
			HeadNode.Words = CurNodeKey;
			HeadNode.AdjPointNumber ++;
			NewNode.Next = null;
			NewNode.Weight = 1;
		    HeadNodeList.addElement(HeadNode);
		}
	}

	public void ReadFileBuildDigraph(String FileName)
	{
		File file = new File(FileName);
		try(
		Reader reader = new InputStreamReader(new FileInputStream(file)))
		{
			int tempchar;
			int CurFlag = 1;
			StringBuffer CurNodeKey = new StringBuffer("");
			StringBuffer PosNodeKey = new StringBuffer("");
			while((tempchar = reader.read()) != -1)
			{
				if (tempchar != '\r')
				{
					int x = ChangeWords(CurFlag, CurNodeKey, PosNodeKey, tempchar);

					CurFlag = x;
				}
		    }


			if (!PosNodeKey.toString().equals(""))
			{
				Insert(CurNodeKey.toString(), PosNodeKey.toString());
				FileText.addElement(CurNodeKey.toString());//
				FileText.addElement(PosNodeKey.toString());//
				if (!NodeList.contains(CurNodeKey.toString()))
			    {
					NodeList.addElement(CurNodeKey.toString());//
			    }
				if (!NodeList.contains(PosNodeKey.toString()))
			    {
					NodeList.addElement(PosNodeKey.toString());//
			    }
			}
			else
			{
				FileText.addElement(FileLastString);//
				if (!NodeList.contains(FileLastString))
			    {
			    	Visit.put(FileLastString, false);
			        NodeList.addElement(FileLastString);
			    }//
			}
	}
	catch(IOException e){
	    e.printStackTrace();}

}

	public int ChangeWords(int CurFlag, StringBuffer CurNodeKey, StringBuffer PosNodeKey, int tempchar)
	{
		if ( ((char)tempchar >= 'a' && (char)tempchar <= 'z')
	     ||  ((char)tempchar >= 'A' && (char)tempchar <= 'Z'))
		{
			if ((char)tempchar >= 'A' && (char)tempchar <= 'Z')
                tempchar += 32;//��Сдת��
			if (CurFlag == 1)
				CurNodeKey.append((char)tempchar);
			else
				PosNodeKey.append((char)tempchar);
		}
		else
		{
			if (CurFlag == 0 && !(PosNodeKey.toString()).equals(""))
			{
				if(!( CurNodeKey.toString()).equals(""))
				{Insert(CurNodeKey.toString(), PosNodeKey.toString());
				FileText.addElement(CurNodeKey.toString());//

				if (!NodeList.contains(CurNodeKey.toString()))
			    {
			    	Visit.put(CurNodeKey.toString(), false);
			        NodeList.addElement(CurNodeKey.toString());
			    }//
				}
				CurNodeKey.replace(0, CurNodeKey.length(), PosNodeKey.toString());
			    CurNodeKey = new StringBuffer(PosNodeKey.toString());
			    FileLastString = PosNodeKey.toString();
			    PosNodeKey.delete(0, PosNodeKey.length());
			    PosNodeKey = new StringBuffer("");
			}
			else
				CurFlag = 0;
		}
		return CurFlag;
	}


	public String GetBridgeWords(String StartString, String EndString)
	{
		String Str;
		BridgeWords.clear();
		KeyNode.clear();
		KeyNode.add(StartString);
		KeyNode.add(EndString);
		Vector<String> BridgeWords = new Vector<String>();
		for (int i = 0; i < HeadNodeList.size(); i++)
		{
			if (StartString.equals(HeadNodeList.get(i).Words))
			{
				if (NodeList.contains(EndString))
				{
					GBridgeWords(StartString, EndString, i);
					if (BridgeWords.size() >= 1)
						return BridgeWords.get(0);
					else
					{
						Str = "No bridge words from " + "\"" + StartString + "\"" +" to \"" + EndString + "\" !";
					    return Str;
					}

				}
				Str =  "No "  + "\"" + EndString + "\" in the graph!";
				return Str;
			}
		}
		if (NodeList.contains(EndString))
			Str = "No "  + "\"" + StartString + "\" in the graph!";
		else
			Str = "No "  + "\"" + StartString + "\"  and \"" + EndString + "\" in the graph!";
		return Str;
	}

	public void GBridgeWords(String StartString, String EndString, int i)
	{
		for (int j = 0; j < NodeList.size(); j++)
			Visit.put(NodeList.get(j), false);
		int Distance = 1;
		DFS(HeadNodeList.get(i), Distance, EndString);
	}

	public void DFS(DigraphNode Node, int Distance, String EndString)
	{
		Visit.put(Node.Words, true);
    	DigraphNode NextNode = Node.Next;
        while (NextNode != null)
=======
    }

    public int changeWords(int curFlag, StringBuffer curNodeKey, StringBuffer posNodeKey, int tempchar) {
        if (((char) tempchar >= 'a' && (char) tempchar <= 'z')
                || ((char) tempchar >= 'A' && (char) tempchar <= 'Z')) {
            if ((char) tempchar >= 'A' && (char) tempchar <= 'Z')
                tempchar += 32;//大小写转换
            if (curFlag == 1)
                curNodeKey.append((char) tempchar);
            else
                posNodeKey.append((char) tempchar);
        } else {
            if (curFlag == 0 && !(posNodeKey.toString()).equals("")) {
                if (!(curNodeKey.toString()).equals("")) {
                    insert(curNodeKey.toString(), posNodeKey.toString());
                    fileText.addElement(curNodeKey.toString());//

                    if (!nodeList.contains(curNodeKey.toString())) {
                        visit.put(curNodeKey.toString(), false);
                        nodeList.addElement(curNodeKey.toString());
                    }//
                }
                curNodeKey.replace(0, curNodeKey.length(), posNodeKey.toString());
                curNodeKey = new StringBuffer(posNodeKey.toString());
                fileLastString = posNodeKey.toString();
                posNodeKey.delete(0, posNodeKey.length());
                posNodeKey = new StringBuffer("");
            } else
                curFlag = 0;
        }
        return curFlag;
    }

    public void gBridgeWords(String endString, int i) {
        for (int j = 0; j < nodeList.size(); j++)
            visit.put(nodeList.get(j), false);
        int Distance = 1;
        DFS(headNodeList.get(i), Distance, endString);
    }

    public void DFS(DigraphNode node, int distance, String endString) {
        visit.put(node.Words, true);
        DigraphNode NextNode = node.Next;
        while (NextNode != null) {
            if (!visit.get(NextNode.Words)) {
                if (distance <= 2) {
                    if (distance == 2) {
                        if (endString.equals(NextNode.Words)) {
                            BridgeWords.addElement(node.Words);
                            KeyNode.addElement(node.Words);
                        }
                    } else {
                        for (int i = 0; i < headNodeList.size(); i++)
                            if ((NextNode.Words).equals(headNodeList.get(i).Words)) {
                                DFS(headNodeList.get(i), distance + 1, endString);
                                break;
                            }
                    }
                }
            }
            NextNode = NextNode.Next;
        }
    }

    public void dInit(PriorityQueue<PQueue> D, Vector<PQueue> nodeShortPath, String start) {
        for (Iterator<String> it = nodeList.iterator(); it.hasNext(); )//遍历点集合

	}

	public String GetNewFiles(String Text)
	{
		String Result = "";
		String[] Words = Text.split(" ");
		Result += Words[0];
		for (int i = 0; i < Words.length - 1; i++)
		{
			BridgeWords.clear();
			GetBridgeWords(Words[i], Words[i + 1]);
			if (BridgeWords.size() != 0)
			{
			    Result += (" " + BridgeWords.get((int)(Math.random() * BridgeWords.size())));
			}
			Result += (" " + Words[i + 1]);
		}
		return Result;
	}

	public String TwoPointsGetShortPath(String Start, String End)
	{
		KeyNode.clear();
		Comparator<PQueue> Order = new Comparator<PQueue>()
		{
			public int compare(PQueue S1, PQueue S2)
			{
				return (S1.Costs - S2.Costs);
			}
		};

		PriorityQueue<PQueue> D = new  PriorityQueue<PQueue>(Order);

		NodeShortPath.clear();

		OnePointGetShortPath(D, NodeShortPath, Start);


		for (int i = 0;i < NodeShortPath.size(); i++)
	    {
	    	if (NodeShortPath.get(i).End.equals(End))
	    	{
	    		ShortPath = NodeShortPath.get(i).P;
	    		break;
	    	}
	    }

	    String S = "\"" + Start + "\" �� " + "\"" + End + "\" ���ɴ�";
	    return S;
}

	public void DInit(PriorityQueue<PQueue> D, Vector<PQueue> NodeShortPath, String Start)
	{
		for (Iterator<String> it = NodeList.iterator(); it.hasNext();)//�����㼯��
	    {
			String Str = it.next();
			PQueue Node = new PQueue();
			PQueue S = new PQueue();//��¼���·��
	        S.Path = new Vector<String>();
			if (!Str.equals(Start))//���������ʼ��
			{
				Node.Costs = 1000000;//��ʼD����
				Node.End = Str;

		        D.add(Node);

		        S.Path.addElement(Start);
		        S.End = Str;
		        NodeShortPath.addElement(S);//�����㵽���е�����·��
			}
	    }
	}

	public void UpdataStart(PriorityQueue<PQueue> D, Vector<PQueue> NodeShortPath, String Start)
	{
		for (int i = 0; i < HeadNodeList.size(); i++)
		{
			if (HeadNodeList.get(i).Words.equals(Start))
			{
				DigraphNode Node  = HeadNodeList.get(i).Next;
				while (Node != null)
				{
					PQueue SNode = new PQueue();
					for (PQueue SP: D)
				        if (SP.End.equals(Node.Words))//�ҵ�ͼ���ڽӵ���D�ж�Ӧ�Ķ���
	    			    {
		    				SNode = SP;
		    				SNode.Costs = Node.Weight;
		    				D.remove(SP);
		    				D.add(SNode);

		    				for (int k = 0; k < NodeShortPath.size(); k++)//����·��
		                    {
		                    	if (NodeShortPath.get(k).End.equals(SNode.End))
		                        {
		                    		NodeShortPath.get(k).Costs = SNode.Costs;
		                    		NodeShortPath.get(k).Path.addElement(SNode.End);
		                    		NodeShortPath.get(k).P.addElement((Start + " " + SNode.End));//
		                    		break;
		                        }
		                    }

		    				break;
	    			    }
					Node = Node.Next;
				}
				break;
			}		}

	}

	public void OnePointGetShortPath(PriorityQueue<PQueue> D, Vector<PQueue> NodeShortPath, String Start)
	{
		DInit(D, NodeShortPath, Start);

        UpdataStart(D, NodeShortPath, Start);
	    while(D.peek() != null)//������
	    {
	    	PQueue Node = D.poll();//���ȶ��е�һ����
	    	String TempNodeString = Node.End;
	    	int Costs = Node.Costs;
	        int i = 0;
	    	for (; i < HeadNodeList.size(); i++)//�ҵ�����ͼ�ж�Ӧ����
	        	if (HeadNodeList.get(i).Words.equals(TempNodeString))
	        		break;
	        if (i != HeadNodeList.size())
	        {
	    	    DigraphNode node = HeadNodeList.get(i).Next;//��ӵ�
	    	    while (node != null)
		    	{
		    		 for (PQueue SP: D)
    	                 if (SP.End.equals(node.Words))//�ҵ�ͼ���ڽӵ���D�ж�Ӧ�Ķ���
    		             {
                            if (Costs +  node.Weight <= SP.Costs)//�õ�ǰ�м������������
                            {
                            	int A = 0;
			    				for (; A < NodeShortPath.size(); A++)
	                        	{
	                        	    if (NodeShortPath.get(A).End.equals(Node.End))
	                        	    	break;
	                        	}

		                        for (int B = 0; B < NodeShortPath.size(); B++)
	                        	{
		                        	if (NodeShortPath.get(B).End.equals(node.Words))
	                        	    {
			                        	if (Costs +  node.Weight == SP.Costs)
	                        	    	{
	                        	    		String S = "";
	                        	    		int I = 0;
	                        	    		for (; I < NodeShortPath.get(A).P.size(); I++)
		                        	    	{
	                        	    			S = "";
	                        	    			S = (NodeShortPath.get(A).P.get(I) + " ");
	                        	    			S += node.Words;
	                        	    			NodeShortPath.get(B).P.add(S);
		                        	    	}

	                        	    	}
	                        	    	else
	                        	    	{
	                        	    		String S = "";
		                        	    	for (int I = 0; I < NodeShortPath.get(A).P.size(); I++)
		                        	    	{
		                        	    		S =  NodeShortPath.get(A).P.get(I);
		                        	    		S += (" " + node.Words);
		                        	    		NodeShortPath.get(B).P.addElement(S);
		                        	    		S = "";
		                        	    	}
	                        	    	}
	                        	    }

	                        	    if (NodeShortPath.get(B).End.equals(node.Words))
	                        	    {
	                        	    	if (Costs +  node.Weight < SP.Costs)
	                        	    	{
	                        	    	    NodeShortPath.get(B).Costs = Costs + node.Weight;//����D;
	                        	    	    PQueue SPNode = SP;
	    			            	        SPNode.Costs = Costs + node.Weight;//����D;
	    			            	        D.remove(SP);
	    				    				D.add(SPNode);
	                        	    	    NodeShortPath.get(B).Path.clear();

	                        	    	}

	                        	    	for (int I = 0; I < NodeShortPath.get(A).Path.size(); I++)
	                        	    		NodeShortPath.get(B).Path.addElement(NodeShortPath.get(A).Path.get(I));
	                        	    	NodeShortPath.get(B).Path.addElement(node.Words);

	                        	    	break;
	                        	    }
	                        	}
=======
    }

    public void updataStart(PriorityQueue<PQueue> D, Vector<PQueue> nodeShortPath, String Start) {
        for (int i = 0; i < headNodeList.size(); i++) {
            if (headNodeList.get(i).Words.equals(Start)) {
                DigraphNode Node = headNodeList.get(i).Next;
                while (Node != null) {
                    PQueue SNode = new PQueue();
                    for (PQueue SP : D)
                        if (SP.End.equals(Node.Words))//找到图的邻接点在D中对应的对象
                        {
                            SNode = SP;
                            SNode.Costs = Node.Weight;
                            D.remove(SP);
                            D.add(SNode);

                            for (int k = 0; k < nodeShortPath.size(); k++)//更新路径
                            {
                                if (nodeShortPath.get(k).End.equals(SNode.End)) {
                                    nodeShortPath.get(k).Costs = SNode.Costs;
                                    nodeShortPath.get(k).Path.addElement(SNode.End);
                                    nodeShortPath.get(k).P.addElement((Start + " " + SNode.End));//
                                    break;
                                }
    		            }
    		        node = node.Next;
		    	  }
	        }
	    }
	}

	public void Traverse()
	{
		for (int i = 0; i < NodeList.size(); i++)
			Visit.put(NodeList.get(i), false);
		for (int i = 0; i < NodeList.size(); i++)
			if (!Visit.get(NodeList.get(i)))
			{
				for (int j = 0; j < HeadNodeList.size(); j++)
				    if (HeadNodeList.get(j).Words.equals(NodeList.get(i)))
				    {
				    	DFSTraverse(HeadNodeList.get(j));
				        break;
				    }
			}
	}

	public void DFSTraverse(DigraphNode Node)
	{
		Visit.put(Node.Words, true);

		DigraphNode NextNode = Node.Next;

		while (NextNode != null)
		{
			if (!Visit.get(NextNode.Words))
			{
				int i = 0;
				for (; i < HeadNodeList.size(); i++)
				    if (HeadNodeList.get(i).Words.equals(NextNode.Words))
				        break;

				if (i != HeadNodeList.size())
		            DFSTraverse(HeadNodeList.get(i));
			}
			NextNode = NextNode.Next;
		}
	}

	public void picture()
	{
		GraphViz gv = new GraphViz();
		gv.addln(gv.start_graph());

		for(DigraphNode node : HeadNodeList)
		{
			while(node != null )
			{
				System.out.print(node.Words+" ");
				node=node.Next;
			}
			System.out.println();
		}

		for(DigraphNode node : HeadNodeList)
		{
			DigraphNode head = node;
			node=node.Next;
			while(node != null )
			{
				gv.addln(head.Words + " -> " + node.Words + "[label="+ node.Weight + "];");
				node=node.Next;
			}
		}

		gv.addln(gv.end_graph());
		//System.out.println(gv.getDotSource());

		String type = "png";
	//	String type = "dot";
	//	String type = "fig";    // open with xfig
	//	String type = "pdf";
	//	String type = "ps";
	//	String type = "svg";    // open with inkscape
	//	String type = "png";
	//	String type = "plain";
		//File out = new File("/out." + type);   // Linux
		File out = new File("D:\\out." + type);    // Windows
		gv.writeGraphToFile( gv.getGraph( gv.getDotSource(), type ), out );
	}

	public void picture_qjc()
	{
		GraphViz gv = new GraphViz();
		gv.addln(gv.start_graph());

		for(DigraphNode node : HeadNodeList)
		{
			DigraphNode head = node;
			node=node.Next;
			while(node != null && KeyNode.size()>=1)
			{
				if(head.Words.equals(KeyNode.get(0)) && !node.Words.equals(KeyNode.get(1)) && KeyNode.contains(node.Words))
				{
					gv.addln(head.Words + " -> " + node.Words + "[color=red, label="+ node.Weight + "];" + head.Words+"[color=red];"+ node.Words+"[color=blue];");
				}
				else if(KeyNode.contains(head.Words) && node.Words.equals(KeyNode.get(1)))
				{
					gv.addln(head.Words + " -> " + node.Words + "[color=red, label="+ node.Weight + "];" + head.Words+"[color=blue];"+ node.Words+"[color=red];");
				}
				else
				{
					gv.addln(head.Words + " -> " + node.Words + "[label="+ node.Weight + "];");
				}
				node=node.Next;
			}
		}

		gv.addln(gv.end_graph());
		//System.out.println(gv.getDotSource());
		/*for(String a : KeyNode)
		{
			System.out.print(a+" ");
		}*/

		String type = "png";
		File out = new File("D:\\qjcout." + type);    // Windows
		gv.writeGraphToFile( gv.getGraph( gv.getDotSource(), type ), out );
	}

	public boolean In(String word1, String word2)
	{
		for(int i=0; i<ShortPath.size(); i++)
		{
			if(KeyNode.contains(word1) && KeyNode.contains(word2) && KeyNode.indexOf(word1, i*KeyNode.size()/ShortPath.size())+1==KeyNode.indexOf(word2, i*KeyNode.size()/ShortPath.size()))
			{
				return true;
			}
		}
		return false;
	}

	public void picture_zdlj()
	{
		GraphViz gv = new GraphViz();
		gv.addln(gv.start_graph());
		KeyNode.clear();

		for(int i=0; i<ShortPath.size(); i++)
		{
			String[] tmp = ShortPath.get(i).split(" ");
			for(int j=0; j<tmp.length; j++)
				KeyNode.add(tmp[j]);
		}

		for(DigraphNode node : HeadNodeList)
		{
			DigraphNode head = node;
			node=node.Next;
			while(node != null && KeyNode.size()>=1)
			{
				if(In(head.Words, node.Words))
				{
					gv.addln(head.Words + " -> " + node.Words + "[color=red, label="+ node.Weight + "];" + head.Words+"[color=red];"+ node.Words+"[color=red];");
				}

				else
				{
					gv.addln(head.Words + " -> " + node.Words + "[label="+ node.Weight + "];");
				}
				node=node.Next;
			}
		}

		gv.addln(gv.end_graph());
		//System.out.println(gv.getDotSource());
		/*for(String a : KeyNode)
		{
			System.out.print(a+" ");
		}*/

		String type = "png";
		File out = new File("D:\\zdljout." + type);    // Windows
		gv.writeGraphToFile( gv.getGraph( gv.getDotSource(), type ), out );
	}

	public boolean In_sjyz(String word1, String word2)
	{
		for(int i=0; i<KeyNode.size()-1; i++)
		{
			if(KeyNode.get(i).equals(word1) && KeyNode.get(i+1).equals(word2))
				return true;
		}
		return false;
	}
	public void picture_sjyz()
	{
		GraphViz gv = new GraphViz();
		gv.addln(gv.start_graph());

		for(DigraphNode node : HeadNodeList)
		{
			DigraphNode head = node;
			node=node.Next;
			while(node != null && KeyNode.size()>=1)
			{
				if(KeyNode.contains(head.Words))
				{
					gv.addln(head.Words+"[color=red]");
				}
				if(KeyNode.contains(node.Words))
				{
					gv.addln(node.Words+"[color=red]");
				}
				if(KeyNode.contains(head.Words) && KeyNode.contains(node.Words) && In_sjyz(head.Words, node.Words))
				{
					gv.addln(head.Words + " -> " + node.Words + "[color=red, label="+ node.Weight + "];" + head.Words+"[color=red];"+ node.Words+"[color=red];");
				}
				else
				{
					gv.addln(head.Words + " -> " + node.Words + "[label="+ node.Weight + "];");
				}
				node=node.Next;
			}
		}

		gv.addln(gv.end_graph());
		//System.out.println(gv.getDotSource());
		/**for(String a : KeyNode)
		{
			System.out.print(a+" ");
		}*/

		String type = "png";
		File out = new File("D:\\sjyzout." + type);    // Windows
		gv.writeGraphToFile( gv.getGraph( gv.getDotSource(), type ), out );
	}

	public String RandomWalk(String pre)
	{
		if(pre=="")//��ʼ��
		{
			KeyNode.clear();
			for(DigraphNode n : HeadNodeList)
			{
				while(n!=null)
				{
					n.flag=false;
					n=n.Next;
				}
			}
			String word = NodeList.get( (int)(NodeList.size() * Math.random() ));
			KeyNode.add(word);
			return word;
		}

		int i;
		for(i=0; i<HeadNodeList.size(); i++)
		{
			if(HeadNodeList.get(i).Words.equals(pre))
				break;
		}
		if(i==HeadNodeList.size())
			return "-end-";

		else
		{
			DigraphNode node = HeadNodeList.get(i);
			int child=(int)(Math.random() * node.AdjPointNumber);
			for(i=0; i<=child ; i++)
			{
				node = node.Next;
			}
			if(node.flag==true)
			{
				KeyNode.add(node.Words);
				return "-end-"+node.Words;
			}
			else
			{
				node.flag=true;
				KeyNode.add(node.Words);
				return node.Words;
			}
		}
	}

	public void StartRandomWalk(BorderPane layout)
	{
		System.out.println("start randomwalking:");
		String word = "";
		String preword ="";
		boolean flag = true;

		do {
			preword = RandomWalk(word);
			if (!preword.equals(word) || !preword.equals("-end-"))
			{
				if (preword.length()>=5 && preword.subSequence(0, 5).equals("-end-"))
				{
					String s = preword.substring(5);
					System.out.print(s + " ");
				}
				else
				{
					System.out.print(preword + " ");
				}
			}
			else
			{
				flag = false;
				System.out.println("End");
			}
			word = preword;
		} while (flag);
	}
}
=======
                        }
                    Node = Node.Next;
                }
                break;
            }
        }

    }

    public String getBridgeWords(String startString, String endString) {
        String Str;
        BridgeWords.clear();
        KeyNode.clear();
        KeyNode.add(startString);
        KeyNode.add(endString);
        Vector<String> bridgeWords = new Vector<>();
        for (int i = 0; i < headNodeList.size(); i++) {
            if (startString.equals(headNodeList.get(i).Words)) {
                if (nodeList.contains(endString)) {
                    gBridgeWords(endString, i);
                    if (bridgeWords.size() >= 1)
                        return bridgeWords.get(0);
                    else {
                        Str = "No bridge words from " + "\"" + startString + "\"" + " to \"" + endString + "\" !";
                        return Str;
                    }

                }
                Str = "No " + "\"" + endString + "\" in the graph!";
                return Str;
            }
        }
        if (nodeList.contains(endString))
            Str = "No " + "\"" + startString + "\" in the graph!";
        else
            Str = "No " + "\"" + startString + "\"  and \"" + endString + "\" in the graph!";
        return Str;
    }

    public String getNewFiles(String text) {
        String Result = "";
        String[] Words = text.split(" ");
        Result += Words[0];
        for (int i = 0; i < Words.length - 1; i++) {
            BridgeWords.clear();
            getBridgeWords(Words[i], Words[i + 1]);
            if (BridgeWords.size() != 0) {
                Result += (" " + BridgeWords.get((int) (Math.random() * BridgeWords.size())));
            }
            Result += (" " + Words[i + 1]);
        }
        return Result;
    }

    public String twoPointsGetShortPath(String start, String end) {
        KeyNode.clear();
        Comparator<PQueue> Order = new Comparator<PQueue>() {
            public int compare(PQueue S1, PQueue S2) {
                return (S1.Costs - S2.Costs);
            }
        };

        PriorityQueue<PQueue> D = new PriorityQueue<>(Order);

        nodeShortPath.clear();

        onePointGetShortPath(D, nodeShortPath, start);


        for (int i = 0; i < nodeShortPath.size(); i++) {
            if (nodeShortPath.get(i).End.equals(end)) {
                shortPath = nodeShortPath.get(i).P;
                break;
            }
        }

        String s = "\"" + start + "\" 与 " + "\"" + end + "\" 不可达";
        return s;
    }

    public void onePointGetShortPath(PriorityQueue<PQueue> D, Vector<PQueue> nodeShortPath, String start) {
        dInit(D, nodeShortPath, start);

        updataStart(D, nodeShortPath, start);
        while (D.peek() != null)//出队列
        {
            PQueue Node = D.poll();//优先队列第一个点
            String TempNodeString = Node.End;
            int Costs = Node.Costs;
            int i = 0;
            for (; i < headNodeList.size(); i++)//找到顶点图中对应顶点
                if (headNodeList.get(i).Words.equals(TempNodeString))
                    break;
            if (i != headNodeList.size()) {
                DigraphNode node = headNodeList.get(i).Next;//领接点
                while (node != null) {
                    for (PQueue SP : D)
                        if (SP.End.equals(node.Words))//找到图的邻接点在D中对应的对象
                        {
                            if (Costs + node.Weight <= SP.Costs)//用当前中间点来其他距离
                            {
                                int A = 0;
                                for (; A < nodeShortPath.size(); A++) {
                                    if (nodeShortPath.get(A).End.equals(Node.End))
                                        break;
                                }

                                for (int B = 0; B < nodeShortPath.size(); B++) {
                                    if (nodeShortPath.get(B).End.equals(node.Words)) {
                                        if (Costs + node.Weight == SP.Costs) {
                                            String S = "";
                                            int I = 0;
                                            for (; I < nodeShortPath.get(A).P.size(); I++) {
                                                S = "";
                                                S = (nodeShortPath.get(A).P.get(I) + " ");
                                                S += node.Words;
                                                nodeShortPath.get(B).P.add(S);
                                            }

                                        } else {
                                            String S = "";
                                            for (int I = 0; I < nodeShortPath.get(A).P.size(); I++) {
                                                S = nodeShortPath.get(A).P.get(I);
                                                S += (" " + node.Words);
                                                nodeShortPath.get(B).P.addElement(S);
                                                S = "";
                                            }
                                        }
                                    }

                                    if (nodeShortPath.get(B).End.equals(node.Words)) {
                                        if (Costs + node.Weight < SP.Costs) {
                                            nodeShortPath.get(B).Costs = Costs + node.Weight;//更新D;
                                            PQueue spnode = SP;
                                            spnode.Costs = Costs + node.Weight;//更新D;
                                            D.remove(SP);
                                            D.add(spnode);
                                            nodeShortPath.get(B).Path.clear();

                                        }

                                        for (int I = 0; I < nodeShortPath.get(A).Path.size(); I++)
                                            nodeShortPath.get(B).Path.addElement(nodeShortPath.get(A).Path.get(I));
                                        nodeShortPath.get(B).Path.addElement(node.Words);

                                        break;
                                    }
                                }
                            }
                            break;
                        }
                    node = node.Next;
                }
            }
        }
    }

    public void picture() {
        GraphViz gv = new GraphViz();
        gv.addln(gv.start_graph());

        for (DigraphNode node : headNodeList) {
            while (node != null) {
                System.out.print(node.Words + " ");
                node = node.Next;
            }
            System.out.println();
        }

        for (DigraphNode node : headNodeList) {
            DigraphNode head = node;
            node = node.Next;
            while (node != null) {
                gv.addln(head.Words + " -> " + node.Words + "[label=" + node.Weight + "];");
                node = node.Next;
            }
        }

        gv.addln(gv.end_graph());

        String type = "png";
        //  String type = "dot";
        //  String type = "fig";    // open with xfig
        //  String type = "pdf";
        //  String type = "ps";
        //  String type = "svg";    // open with inkscape
        //  String type = "png";
        //  String type = "plain";
        //File out = new File("/out." + type);   // Linux
        File out = new File("D:\\out." + type);    // Windows
        gv.writeGraphToFile(gv.getGraph(gv.getDotSource(), type), out);
    }

    public void pictureQjc() {
        GraphViz gv = new GraphViz();
        gv.addln(gv.start_graph());

        for (DigraphNode node : headNodeList) {
            DigraphNode head = node;
            node = node.Next;
            while (node != null && KeyNode.size() >= 1) {
                if (head.Words.equals(KeyNode.get(0)) && !node.Words.equals(KeyNode.get(1)) && KeyNode.contains(node.Words)) {
                    gv.addln(head.Words + " -> " + node.Words + "[color=red, label=" + node.Weight + "];" + head.Words + "[color=red];" + node.Words + "[color=blue];");
                } else if (KeyNode.contains(head.Words) && node.Words.equals(KeyNode.get(1))) {
                    gv.addln(head.Words + " -> " + node.Words + "[color=red, label=" + node.Weight + "];" + head.Words + "[color=blue];" + node.Words + "[color=red];");
                } else {
                    gv.addln(head.Words + " -> " + node.Words + "[label=" + node.Weight + "];");
                }
                node = node.Next;
            }
        }

        gv.addln(gv.end_graph());
        //System.out.println(gv.getDotSource());
        /*for(String a : KeyNode)
        {
            System.out.print(a+" ");
        }*/

        String type = "png";
        File out = new File("D:\\qjcout." + type);    // Windows
        gv.writeGraphToFile(gv.getGraph(gv.getDotSource(), type), out);
    }

    public boolean in(String word1, String word2) {
        for (int i = 0; i < shortPath.size(); i++) {
            if (KeyNode.contains(word1) && KeyNode.contains(word2) && KeyNode.indexOf(word1, i * KeyNode.size() / shortPath.size()) + 1 == KeyNode.indexOf(word2, i * KeyNode.size() / shortPath.size())) {
                return true;
            }
        }
        return false;
    }

    public void pictureZdlj() {
        GraphViz gv = new GraphViz();
        gv.addln(gv.start_graph());
        KeyNode.clear();

        for (int i = 0; i < shortPath.size(); i++) {
            String[] tmp = shortPath.get(i).split(" ");
            for (int j = 0; j < tmp.length; j++)
                KeyNode.add(tmp[j]);
        }

        for (DigraphNode node : headNodeList) {
            DigraphNode head = node;
            node = node.Next;
            while (node != null && KeyNode.size() >= 1) {
                if (in(head.Words, node.Words)) {
                    gv.addln(head.Words + " -> " + node.Words + "[color=red, label=" + node.Weight + "];" + head.Words + "[color=red];" + node.Words + "[color=red];");
                } else {
                    gv.addln(head.Words + " -> " + node.Words + "[label=" + node.Weight + "];");
                }
                node = node.Next;
            }
        }

        gv.addln(gv.end_graph());

        String type = "png";
        File out = new File("D:\\zdljout." + type);    // Windows
        gv.writeGraphToFile(gv.getGraph(gv.getDotSource(), type), out);
    }

    public boolean inSjyz(String word1, String word2) {
        for (int i = 0; i < KeyNode.size() - 1; i++) {
            if (KeyNode.get(i).equals(word1) && KeyNode.get(i + 1).equals(word2))
                return true;
        }
        return false;
    }

    public void pictureSjyz() {
        GraphViz gv = new GraphViz();
        gv.addln(gv.start_graph());

        for (DigraphNode node : headNodeList) {
            DigraphNode head = node;
            node = node.Next;
            while (node != null && KeyNode.size() >= 1) {
                if (KeyNode.contains(head.Words)) {
                    gv.addln(head.Words + "[color=red]");
                }
                if (KeyNode.contains(node.Words)) {
                    gv.addln(node.Words + "[color=red]");
                }
                if (KeyNode.contains(head.Words) && KeyNode.contains(node.Words) && inSjyz(head.Words, node.Words)) {
                    gv.addln(head.Words + " -> " + node.Words + "[color=red, label=" + node.Weight + "];" + head.Words + "[color=red];" + node.Words + "[color=red];");
                } else {
                    gv.addln(head.Words + " -> " + node.Words + "[label=" + node.Weight + "];");
                }
                node = node.Next;
            }
        }

        gv.addln(gv.end_graph());

        String type = "png";
        File out = new File("D:\\sjyzout." + type);    // Windows
        gv.writeGraphToFile(gv.getGraph(gv.getDotSource(), type), out);
    }

    public String RandomWalk(String pre) {
        if (pre.equals(""))//初始化
        {
            KeyNode.clear();
            for (DigraphNode n : headNodeList) {
                while (n != null) {
                    n.flag = false;
                    n = n.Next;
                }
            }
            String word = nodeList.get((int) (nodeList.size() * Math.random()));
            KeyNode.add(word);
            return word;
        }

        int i;
        for (i = 0; i < headNodeList.size(); i++) {
            if (headNodeList.get(i).Words.equals(pre))
                break;
        }
        if (i == headNodeList.size())
            return "-end-";

        else {
            DigraphNode node = headNodeList.get(i);
            int child = (int) (Math.random() * node.AdjPointNumber);
            for (i = 0; i <= child; i++) {
                node = node.Next;
            }
            if (node.flag) {
                KeyNode.add(node.Words);
                return "-end-" + node.Words;
            } else {
                node.flag = true;
                KeyNode.add(node.Words);
                return node.Words;
            }
        }
    }
}