package components
{
import mx.core.ILayoutElement;

import spark.components.supportClasses.GroupBase;
import spark.layouts.supportClasses.LayoutBase;

public class FlowLayout extends LayoutBase
{
    //---------------------------------------------------------------
    //
    //  Class properties
    //
    //---------------------------------------------------------------
    
	
	private var _paddingLeft:Number = 0;

	public function set paddingLeft(value:Number):void
	{
		_paddingLeft = value;
		// We must invalidate the layout
		var layoutTarget:GroupBase = target;
		if (layoutTarget)
		{
			layoutTarget.invalidateSize();
			layoutTarget.invalidateDisplayList();
		}
	}

	private var _paddingRight:Number = 0;

	public function set paddingRight(value:Number):void
	{
		_paddingRight = value;
		// We must invalidate the layout
		var layoutTarget:GroupBase = target;
		if (layoutTarget)
		{
			layoutTarget.invalidateSize();
			layoutTarget.invalidateDisplayList();
		}
	}

	private var _paddingTop:Number = 0;

	public function set paddingTop(value:Number):void
	{
		_paddingTop = value;
		// We must invalidate the layout
		var layoutTarget:GroupBase = target;
		if (layoutTarget)
		{
			layoutTarget.invalidateSize();
			layoutTarget.invalidateDisplayList();
		}		
	}

	private var _paddingBottom:Number = 0;

	public function set paddingBottom(value:Number):void
	{
		_paddingBottom = value;
		// We must invalidate the layout
		var layoutTarget:GroupBase = target;
		if (layoutTarget)
		{
			layoutTarget.invalidateSize();
			layoutTarget.invalidateDisplayList();
		}
	}

	
    //---------------------------------------------------------------
    //  horizontalGap
    //---------------------------------------------------------------
    
    private var _horizontalGap:Number = 6;
    
    public function set horizontalGap(value:Number):void
    {
        _horizontalGap = value;
        
        // We must invalidate the layout
        var layoutTarget:GroupBase = target;
        if (layoutTarget)
        {
            layoutTarget.invalidateSize();
            layoutTarget.invalidateDisplayList();
        }
    }
	
	//---------------------------------------------------------------
	//  horizontalGap
	//---------------------------------------------------------------
	private var _verticalGap:Number = 6;
	
	public function set verticalGap(value:Number):void
	{
		_verticalGap = value;
		
		// We must invalidate the layout
		var layoutTarget:GroupBase = target;
		if (layoutTarget)
		{
			layoutTarget.invalidateSize();
			layoutTarget.invalidateDisplayList();
		}
	}
    
    //---------------------------------------------------------------
    //
    //  Class methods
    //
    //---------------------------------------------------------------
    
    override public function measure():void
    {
        var totalWidth:Number = _paddingLeft + _paddingRight;
        var totalHeight:Number = _paddingBottom + _paddingTop;

        // loop through the elements
        var layoutTarget:GroupBase = target;
        var count:int = layoutTarget.numElements;
        for (var i:int = 0; i < count; i++)
        {
            // get the current element, we're going to work with the
            // ILayoutElement interface
            var element:ILayoutElement = useVirtualLayout ? 
                layoutTarget.getVirtualElementAt(i) :
                layoutTarget.getElementAt(i);
            
            // In virtualization scenarios, the element returned could
            // still be null. Look at the typical element instead.
            if (!element)
                element = typicalLayoutElement;

            // Find the preferred sizes    
            var elementWidth:Number = element.getPreferredBoundsWidth();
            var elementHeight:Number = element.getPreferredBoundsHeight();
			
			var elementX:Number = Math.max(element.getBoundsXAtSize(NaN, NaN), _paddingLeft);
			var elementY:Number = Math.max(element.getBoundsYAtSize(NaN, NaN), _paddingTop);
            
            totalWidth = Math.max(totalWidth, elementX + elementWidth + _paddingRight);
            totalHeight = Math.max(totalHeight, elementY + elementHeight + _paddingBottom);
        }
        
        layoutTarget.measuredWidth = totalWidth;
        layoutTarget.measuredHeight = totalHeight;
        
        // Since we really can't fit the content in space any
        // smaller than this, set the measured minimum size
        // to be the same as the measured size.
        // If the container is clipping and scrolling, it will
        // ignore these limits and will still be able to 
        // shrink below them.
        layoutTarget.measuredMinWidth = totalWidth;
        layoutTarget.measuredMinHeight = totalHeight;			
    }

    override public function updateDisplayList(containerWidth:Number,
                                               containerHeight:Number):void
    {
        // The position for the current element
        var x:Number = _paddingLeft;
        var y:Number = _paddingTop;
        var maxWidth:Number = 0;
        var maxHeight:Number = 0;
        
        // loop through the elements
        var layoutTarget:GroupBase = target;
        var count:int = layoutTarget.numElements;
        for (var i:int = 0; i < count; i++)
        {
            // get the current element, we're going to work with the
            // ILayoutElement interface
            var element:ILayoutElement = useVirtualLayout ? 
                layoutTarget.getVirtualElementAt(i) :
                layoutTarget.getElementAt(i);
            
            // Resize the element to its preferred size by passing
            // NaN for the width and height constraints
            element.setLayoutBoundsSize(NaN, NaN);
            
            // Find out the element's dimensions sizes.
            // We do this after the element has been already resized
            // to its preferred size.
            var elementWidth:Number = element.getLayoutBoundsWidth();
            var elementHeight:Number = element.getLayoutBoundsHeight();
            
            // Would the element fit on this line, or should we move
            // to the next line?
            if (x + elementWidth > containerWidth - _paddingRight)
            {
                // Start from the left side
                x = _paddingLeft;
                
                // Move down by elementHeight, we're assuming all 
                // elements are of equal height
                y += elementHeight + _verticalGap;
            }
            
            // Position the element
            element.setLayoutBoundsPosition(x, y);
            
            // Find maximum element extents. This is needed for
            // the scrolling support.
            maxWidth = Math.max(maxWidth, x + elementWidth + _paddingRight);
            maxHeight = Math.max(maxHeight, y + elementHeight + _paddingBottom);
            
            // Update the current position, add the gap
            x += elementWidth + _horizontalGap;
        }
        
        // Scrolling support - update the content size
        layoutTarget.setContentSize(maxWidth, maxHeight);
		measure();
    }
}
}