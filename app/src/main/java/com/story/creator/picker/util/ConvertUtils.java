package com.story.creator.picker.util;


import android.graphics.Color;

public class ConvertUtils {

    private double hHSV = 0;
    private double sHSV = 0;
    private double vHSV = 0;
    public void toHSV3( int red , int green , int blue ){
        double 	maxRGB	=	max( red , green , blue );//
        double	minRGB	=	min( red , green , blue );
        double	itemp	=	maxRGB;            //v'=itemp
        double	temp	=	maxRGB	-	minRGB;//

        if( maxRGB == minRGB ) {
            this.hHSV	=	0;
            this.sHSV	=	0;
            this.vHSV	=	maxRGB / 255;
            return;
        }
        double	rtemp	=	( itemp - red )	/ temp;//r'=rtemp
        double	gtemp	=	( itemp - green ) / temp;//g'=gtemp
        double	btemp	=	( itemp - blue ) / temp;//b'=btemp
        this.vHSV	=	itemp / 255;//v=this.vHSV
        this.sHSV	=	temp / itemp;//s'=this.sHSV
        if( red == maxRGB ){
            if( green == minRGB )
                this.hHSV	=	5 + btemp;
            else
                this.hHSV	=	1 - gtemp;
        }
        else if( green == maxRGB ){
            if( blue == minRGB )
                this.hHSV	=	1 + rtemp;
            else
                this.hHSV	=	3 - btemp;
        }
        else if( blue == maxRGB ){
            if( red == minRGB )
                this.hHSV	=	3 + gtemp;
            else
                this.hHSV	=	5 - rtemp;
        }
        this.hHSV	*=	60;
    }

    private int max(int red, int green, int blue) {
        return Math.max(Math.max(red, green), Math.max(red, blue));
    }

    private int min(int red, int green, int blue) {
        return Math.min(Math.min(red, green), Math.min(red, blue));
    }
}
