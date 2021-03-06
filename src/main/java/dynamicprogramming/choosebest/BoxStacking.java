package dynamicprogramming.choosebest;

/*
 * created by raghavendra.ta on 14-Jul-2021
 */

import java.util.Arrays;

/**
 * H(j) = max(i < j and wi > wj and di > dj) {H[i]} + hj
 */
public class BoxStacking {

    static class Box implements Comparable<Box>{

        // h --> height, w --> width,
        // d --> depth
        int h, w, d, area;

        // for simplicity of solution,
        // always keep w <= d

        /*Constructor to initialise object*/
        public Box(int h, int w, int d) {
            this.h = h;
            this.w = w;
            this.d = d;
        }

        /*To sort the box array on the basis
        of area in decreasing order of area */
        @Override
        public int compareTo(Box o) {
            return o.area-this.area;
        }
    }

    static int maxStackHeight( Box arr[], int n){

        Box[] rot = new Box[n*3];

        /* New Array of boxes is created -
        considering all 3 possible rotations,
        with width always greater than equal
        to width */
        for(int i = 0;i < n;i++){
            Box box = arr[i];

            /* Original Box*/
            rot[3*i] = new Box(box.h, Math.max(box.w,box.d),
                               Math.min(box.w,box.d));

            /* First rotation of box*/
            rot[3*i + 1] = new Box(box.w, Math.max(box.h,box.d),
                                   Math.min(box.h,box.d));

            /* Second rotation of box*/
            rot[3*i + 2] = new Box(box.d, Math.max(box.w,box.h),
                                   Math.min(box.w,box.h));
        }

        /* Calculating base area of
        each of the boxes.*/
        for(int i = 0; i < rot.length; i++)
            rot[i].area = rot[i].w * rot[i].d;

        /* Sorting the Boxes on the bases
        of Area in non Increasing order.*/
        Arrays.sort(rot);

        int count = 3 * n;

        /* Initialize msh values for all
        indexes
        msh[i] --> Maximum possible Stack Height
                   with box i on top */
        int[]msh = new int[count];
        for (int i = 0; i < count; i++ )
            msh[i] = rot[i].h;

        /* Computing optimized msh[]
        values in bottom up manner */
        for(int i = 0; i < count; i++){
            msh[i] = 0;
            Box box = rot[i];
            int val = 0;

            for(int j = 0; j < i; j++){
                Box prevBox = rot[j];
                if(box.w < prevBox.w && box.d < prevBox.d){
                    val = Math.max(val, msh[j]);
                }
            }
            msh[i] = val + box.h;
        }

        int max = -1;

        /* Pick maximum of all msh values */
        for(int i = 0; i < count; i++){
            max = Math.max(max, msh[i]);
        }

        return max;
    }

}
