package com.kverchi.tools;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class Pair <width, height> {
    private width Widht;
    private height Height;
    public Pair(width W, height H){
        this.Widht = W;
        this.Height = H;
    }
    public Pair() { }
	public width getWidht() {
		return Widht;
	}
	public void setWidht(width _widht) {
		Widht = _widht;
	}
	public height getHeight() {
		return Height;
	}
	public void setHeight(height _height) {
		Height = _height;
	}
    
}
