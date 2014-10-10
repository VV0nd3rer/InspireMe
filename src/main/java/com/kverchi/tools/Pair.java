package com.kverchi.tools;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class Pair <First, Second> {
    private First first;
    private Second second;
    public Pair(First first, Second second){
        this.first = first;
        this.second = second;
    }
    public Pair() { }
	public First getFirst() {
		return this.first;
	}
	public void setFirst(First first) {
		this.first = first;
	}
	public Second getSecond() {
		return this.second;
	}
	public void setSecond(Second second) {
		this.second = second;
	}
    
}
