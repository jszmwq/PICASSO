package iisc.dsl.picasso.client.util;

import java.util.*;

import iisc.dsl.picasso.client.panel.MainPanel;
import iisc.dsl.picasso.common.PicassoConstants;
import iisc.dsl.picasso.common.ds.DataValues;
import iisc.dsl.picasso.common.ds.DiagramPacket;

import java.io.IOException;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

import java.text.SimpleDateFormat;  


public class StoreDiagram {

    // 定义点结构体
    public static class Point implements Comparable<Point> {
        float x, y;

        Point(float x, float y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(Point other) {
            if (this.x == other.x) {
                return Float.compare(this.y, other.y);
            }
            return Float.compare(this.x, other.x);
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            Point point = (Point) obj;
            return Float.compare(point.x, x) == 0 && Float.compare(point.y, y) == 0;
        }

        @Override
        public int hashCode() {
            return hashCode(x, y);
        }

        private int hashCode(float x, float y) {
            int result = 17;
            result = 31 * result + Float.floatToIntBits(x);
            result = 31 * result + Float.floatToIntBits(y);
            return result;
        }
    }
    
    // 定义网格结构体
    public static class Grid {
        Point center;
        int value;
        int row;
        int col;

        Grid(float x, float y, int value, int row, int col) {
            this.center = new Point(x, y);
            this.value = value;
            this.row = row;
            this.col = col;
        }
    }

    public static class Edge {
        Point start;
        Point end;

        Edge(Point start, Point end) {
            this.start = start;
            this.end = end;
        }
    }

// 获取网格顶点
    public static HashMap<Point, Integer> getGridVertices(List<Grid> grids, int NROWS, int NCOLS) {
        int precisionX, precisionY;
        if (NROWS == 30 || NROWS == 300) {
            precisionY = 2;
        } else if (NROWS == 10 || NROWS == 100) {
            precisionY = 0;
        } else if (NROWS == 1000) {
            precisionY = 1;
        } else {
            precisionY = 2;
        }

        if (NCOLS == 30 || NCOLS == 300) {
            precisionX = 2;
        } else if (NCOLS == 10 || NCOLS == 100) {
            precisionX = 0;
        } else if (NCOLS == 1000) {
            precisionX = 1;
        } else {
            precisionX = 2;
        }

        HashMap<Point, Integer> pointCountMap = new HashMap<Point,Integer>();

        for (Grid grid : grids) {
            Point center = grid.center;
            float halfResX = (float)(100.0f/(2.0f*NCOLS));
            float halfResY = (float)(100.0f/(2.0f*NROWS));
            float x, y;

            //lb
            x = center.x - halfResX;
            y = center.y - halfResY;
            String formattedX = String.format("%." + precisionX + "f", x);
            x = Float.parseFloat(formattedX);
            String formattedY = String.format("%." + precisionY + "f", y);
            y = Float.parseFloat(formattedY);
            Point lb = new Point(x, y);
            if (pointCountMap.containsKey(lb)) {
                pointCountMap.put(lb, pointCountMap.get(lb) + 1);
            } else {
                pointCountMap.put(lb, 1);
            }

            //lt
            x = center.x - halfResX;
            y = center.y + halfResY;
            formattedX = String.format("%." + precisionX + "f", x);
            x = Float.parseFloat(formattedX);
            formattedY = String.format("%." + precisionY + "f", y);
            y = Float.parseFloat(formattedY);
            Point lt = new Point(x, y);
            if (pointCountMap.containsKey(lt)) {
                pointCountMap.put(lt, pointCountMap.get(lt) + 1);
            } else {
                pointCountMap.put(lt, 1);
            }

            //rt
            x = center.x + halfResX;
            y = center.y + halfResY;
            formattedX = String.format("%." + precisionX + "f", x);
            x = Float.parseFloat(formattedX);
            formattedY = String.format("%." + precisionY + "f", y);
            y = Float.parseFloat(formattedY);
            Point rt = new Point(x, y);
            if (pointCountMap.containsKey(rt)) {
                pointCountMap.put(rt, pointCountMap.get(rt) + 1);
            } else {
                pointCountMap.put(rt, 1);
            }

            //rb
            x = center.x + halfResX;
            y = center.y - halfResY;
            formattedX = String.format("%." + precisionX + "f", x);
            x = Float.parseFloat(formattedX);
            formattedY = String.format("%." + precisionY + "f", y);
            y = Float.parseFloat(formattedY);
            Point rb = new Point(x, y);
            if (pointCountMap.containsKey(rb)) {
                pointCountMap.put(rb, pointCountMap.get(rb) + 1);
            } else {
                pointCountMap.put(rb, 1);
            }
        }
        return pointCountMap;
    }

    public static List<Grid> findAdjacent(Grid currentGrid, Grid[][] AllGrids, boolean[][] found) {

        List<Grid> neighbors = new ArrayList<Grid>();

        int NROWS = AllGrids.length;
        int NCOLS = AllGrids[0].length;
        int row = currentGrid.row;
        int col = currentGrid.col;
        found[row][col] = true;

        //down
        if (row > 0 && currentGrid.value == AllGrids[row-1][col].value && found[row-1][col] == false) {
            neighbors.add(AllGrids[row-1][col]);
            found[row-1][col] = true;
        }
        //up
        if (row < NROWS - 1 && currentGrid.value == AllGrids[row+1][col].value && found[row+1][col] == false) {
            neighbors.add(AllGrids[row+1][col]);
            found[row+1][col] = true;
        }
        //left
        if (col > 0 && currentGrid.value == AllGrids[row][col-1].value && found[row][col-1] == false) {
            neighbors.add(AllGrids[row][col-1]);
            found[row][col-1] = true;
        }
        //right
        if (col < NCOLS - 1 && currentGrid.value == AllGrids[row][col+1].value && found[row][col+1] == false) {
            neighbors.add(AllGrids[row][col+1]);
            found[row][col+1] = true;
        }
        return neighbors;
    }

    public static List<Point> removeVertices(HashMap<Point, Integer> pointCountMap, int NROWS, int NCOLS) {
        List<Point> vertices = new ArrayList<Point>();
        for (Map.Entry<Point, Integer> entry : pointCountMap.entrySet()) {
            if (entry.getValue() % 2 != 0) {
                vertices.add(entry.getKey());
            }
        }
        return vertices;
    }


    public static Point findNextPoint (Point point, Map<Point, Point> pointEdgeMap) {
        return pointEdgeMap.get(point);
    }

    public static Map <Point, Point> buildPointEdgeMap(List<Edge> edges) {
        Map<Point, Point> pointEdgeMap = new HashMap<Point,Point>();
        for (Edge edge : edges) {
            pointEdgeMap.put(edge.start, edge.end);
            pointEdgeMap.put(edge.end, edge.start);
        }
        return pointEdgeMap;
    }

    public static List<Point> sortVertice(List<Point> vertices, final int NROWS, final int NCOLS) {
        List<Point> sortedByY = new ArrayList<Point>(vertices);
        Collections.sort(sortedByY, new Comparator<Point>() {
            @Override
            public int compare(Point p1, Point p2) {
                if (Math.abs(p1.y - p2.y) > (float) (100/(2*NROWS))) {
                    return Float.compare(p1.y, p2.y);
                } else {
                    return Float.compare(p1.x, p2.x);
                }
            }
        });
        List<Point> sortedByX = new ArrayList<Point>(vertices);
        Collections.sort(sortedByX, new Comparator<Point>() {
            @Override
            public int compare(Point p1, Point p2) {
                if ((Math.abs(p1.x - p2.x) > (float) (100/(2*NCOLS)))) {
                    return Float.compare(p1.x, p2.x);
                } else {
                    return Float.compare(p1.y, p2.y);
                }
            }
        });

        List<Edge> RowEdges = new ArrayList<Edge>();
        for (int i=0; i<sortedByY.size(); i+=2) {
            RowEdges.add(new Edge(sortedByY.get(i), sortedByY.get(i+1)));
        }
        List<Edge> ColEdges = new ArrayList<Edge>();
        for (int i=0; i<sortedByX.size(); i+=2) {
            ColEdges.add(new Edge(sortedByX.get(i), sortedByX.get(i+1)));
        }

        Map<Point, Point> RowPointEdgeMap = buildPointEdgeMap(RowEdges);
        Map<Point, Point> ColPointEdgeMap = buildPointEdgeMap(ColEdges);

        List<Point> sortedVertices = new ArrayList<Point>();
        int numEdge = RowEdges.size() + ColEdges.size();
        int count = 0;
        Point initialPoint = ColEdges.get(0).start;
        Point start = ColEdges.get(0).start;
        sortedVertices.add(start);

        boolean findByY = true;
        while (count < numEdge) {
            if (findByY) {
                Point next = findNextPoint(start, RowPointEdgeMap);
                if (initialPoint != next) {
                    sortedVertices.add(next);
                    start = next;
                    findByY = false;
                    count++;
                } else {
                    break;
                }
            } else {
                Point next = findNextPoint(start, ColPointEdgeMap);
                if (initialPoint != next) {
                    sortedVertices.add(next);
                    start = next;
                    findByY = true;
                    count++;
                } else {
                    break;
                }
            } 
        }
        return sortedVertices;
    }

    // 合并网格并记录多边形顶点
    public static void mergeGrids(Map<Integer, List<Grid>> planidToGridMap, Grid[][] AllgridsArray, int NROWS, int NCOLS) {

        boolean ClearFlag = true;
        int planNumber = planidToGridMap.size();
        for (Map.Entry<Integer, List<Grid>> entry : planidToGridMap.entrySet()) {
            int planid = entry.getKey();
            System.out.println(" Planid :  " + planid );
            if (planid == 6) {
                int tmp = 0;
            }

            // 创建一个Date对象来获取当前时间  
            Date now = new Date();  
            // 创建一个SimpleDateFormat对象来定义日期时间格式  
            // 例如："yyyy-MM-dd HH:mm:ss" 表示年-月-日 时:分:秒  
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
            // 使用SimpleDateFormat对象的format方法将Date对象格式化为String  
            String formattedDate = sdf.format(now);  
            // 打印格式化后的时间  
            System.out.println("start时间: " + formattedDate);  

            List<Grid> GridCenters = entry.getValue();
            boolean[][] found = new boolean[NROWS][NCOLS];

    //        while (!GridCenters.isEmpty()) {

            for (int index = 0; index < GridCenters.size(); index++) {
                Grid currentGrid = GridCenters.get(index);
                int row = currentGrid.row;
                int col = currentGrid.col;
                if (found[row][col] == true) {
                    continue;
                }

                long startTime = System.currentTimeMillis();
                List<Grid> neiborGrids = new ArrayList<Grid>();
                neiborGrids.add(currentGrid);

                HashMap<Point, Integer> AllGridsVertices = new HashMap<Point,Integer>();
                List<Point> polygonVertices = new ArrayList<Point>();

                int innerIndex = 0;
                while (innerIndex < neiborGrids.size()) {
                    currentGrid = neiborGrids.get(innerIndex);
                    neiborGrids.addAll(findAdjacent(currentGrid, AllgridsArray, found));
                    innerIndex++;
                }

                // if (neiborGrids.size() == GridCenters.size()) {
                //     GridCenters.clear();
                // } else {
                //     GridCenters.removeAll(neiborGrids);
                // }

                long endTime = System.currentTimeMillis();
                System.out.println(" findAdjacent Time  :  " + (endTime - startTime) );

                startTime = System.currentTimeMillis();
                AllGridsVertices = getGridVertices(neiborGrids, NROWS, NCOLS);
                endTime = System.currentTimeMillis();
                System.out.println(" getGridVertices Time  :  " + (endTime - startTime) );

                startTime = System.currentTimeMillis();
                polygonVertices = removeVertices(AllGridsVertices, NROWS, NCOLS);
                endTime = System.currentTimeMillis();
                System.out.println(" removeVertices Time  :  " + (endTime - startTime) );



                startTime = System.currentTimeMillis();

                //sort
                polygonVertices = sortVertice(polygonVertices, NROWS, NCOLS);
                String filename = "/home/liuziang/Desktop/plandiagram/POLYGON/polygons.txt";
                writePolygonToFile(filename, polygonVertices, planid, ClearFlag, planNumber);
                ClearFlag = false;

                endTime = System.currentTimeMillis();
                long sortVerticeTime = endTime - startTime;
                System.out.println("sortVertice Time: " + sortVerticeTime + "ms");
            }


            // 创建一个Date对象来获取当前时间  
            Date end = new Date();  
            formattedDate = sdf.format(end);  
            // 打印格式化后的时间  
            System.out.println("end时间: " + formattedDate);
        }
    }

    // 将多边形写入文件 
    public static void writePolygonToFile(String filename, List<Point> polygon, int planid, boolean ClearFlag, int planNumber) {
        BufferedWriter writer = null; 
        try {
            // clear txt
            if (ClearFlag == true) {
                FileOutputStream fos = new FileOutputStream(filename, false);
                OutputStreamWriter osw = new OutputStreamWriter(fos, "UTF-8");
                writer = new BufferedWriter(osw);
                writer.write(planNumber + "\n");
                writer.close();
            }

            FileOutputStream fos = new FileOutputStream(filename, true);
            OutputStreamWriter osw = new OutputStreamWriter(fos, "UTF-8");
            writer = new BufferedWriter(osw);

            writer.write("sep\n"); // 分隔符 
            writer.write(planid + "\n"); // planid 
            writer.write(polygon.size() + "\n"); // 写入多边形的vertex数量 
            for (Point vertex : polygon) {
                writer.write(vertex.x + "," + vertex.y + "\n"); // 多边形的顶点值 
            } 
                writer.close(); 
        } catch (IOException e) {
            e.printStackTrace(); 
        } 
    }


    public static void recordPolygons(int type, MainPanel panel, DiagramPacket gdp) {

        int NROWS=1;
		int maxConditions = gdp.getDimension();
		if(maxConditions!=1)
			NROWS = gdp.getResolution(PicassoConstants.a[1]);
		int NCOLS = gdp.getResolution(PicassoConstants.a[0]);
		// int[][] sortedPlan;
		
		// if ( type == 1 )
		// 	sortedPlan = panel.getExecSortedPlan();
		// else
		// 	sortedPlan = panel.getSortedPlan();
		
		DataValues[] data = gdp.getData();
        float[] sValues = gdp.getPicassoSelectivity();
        int planid;
        Map<Integer, List<Grid>> planidToGridMap = new HashMap<Integer, List<Grid>>();
        Grid[][] AllgridsArray = new Grid[NROWS][NCOLS];

        for (int i=0; i < NROWS; i++) {
            for (int j=0; j < NCOLS; j++) {
                Point gridCenter = new Point(sValues[j], sValues[i + gdp.getResolution(0)]);
                planid = data[i*NCOLS+j].getPlanNumber();
            //    planid = sortedPlan[0][data[i*NCOLS+j].getPlanNumber()];
                Grid grid = new Grid(gridCenter.x, gridCenter.y, planid, i, j);
                AllgridsArray[i][j] = grid;
                if (!planidToGridMap.containsKey(planid)) {
                    planidToGridMap.put(planid, new ArrayList<Grid>());
                }
                planidToGridMap.get(planid).add(grid);
            }
        }
        mergeGrids(planidToGridMap, AllgridsArray, NROWS, NCOLS);
    }
}