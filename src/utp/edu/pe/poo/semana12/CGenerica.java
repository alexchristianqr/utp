package semana12;

public class CGenerica<T,P> {
    T objPricipal;
    P objSecondary;

    public CGenerica(T objPricipal, P objSecondary) {
        this.objPricipal = objPricipal;
        this.objSecondary = objSecondary;
    }

    public T getObjPricipal() {
        return objPricipal;
    }

    public void setObjPricipal(T objPricipal) {
        this.objPricipal = objPricipal;
    }

    public P getObjSecondary() {
        return objSecondary;
    }

    public void setObjSecondary(P objSecondary) {
        this.objSecondary = objSecondary;
    }
    
    
    
    
}
