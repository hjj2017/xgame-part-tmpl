package com.game.part.tmpl.type;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import com.game.part.tmpl.XSSFRowReadStream;
import com.game.part.tmpl.XlsxTmplError;

/**
 * 列表字段
 * 
 * @author hjj2019
 * @since 2015/2/23
 * 
 */
public class XlsxArrayList<T extends AbstractXlsxCol> extends AbstractXlsxCol implements List<T> {
    /** 数值列表 */
    private final List<T> _objValList = new ArrayList<>();

    /**
     * 类参数构造器
     *
     * @param num
     * @param clazzOfCol
     *
     */
    public XlsxArrayList(int num, Class<T> clazzOfCol) {
        if (num <= 0 ||
            clazzOfCol == null) {
            return;
        }

        for (int i = 0; i < num; i++) {
            try {
                // 创建对象并添加到列表
                this._objValList.add(clazzOfCol.newInstance());
            } catch (Exception ex) {
                // 包装并抛出异常!
                throw new XlsxTmplError(ex);
            }
        }
    }

    /**
     * 类参数构造器
     *
     * @param tArr
     *
     */
    @SuppressWarnings("unchecked")
    public XlsxArrayList(T ... tArr) {
        if (tArr == null ||
            tArr.length <= 0) {
            return;
        } else {
            Collections.addAll(this._objValList, tArr);
        }
    }

    @Override
    public void validate() {
        if (this._objValList == null ||
            this._objValList.isEmpty()) {
            return;
        }

        this._objValList.forEach(o -> {
            if (o != null) {
                o.validate();
            }
        });
    }

    @Override
    protected void readImpl(XSSFRowReadStream fromStream) {
        if (fromStream == null ||
            this._objValList == null ||
            this._objValList.isEmpty()) {
            return;
        } else {
            this._objValList.forEach(o -> {
                // 断言参数不为空
                assert o != null : "o";
                // 读取行数据
                o.readFrom(fromStream);
            });
        }
    }

    @Override
    public int size() {
        return this._objValList.size();
    }

    @Override
    public boolean isEmpty() {
        return this._objValList.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return this._objValList.contains(o);
    }

    @Override
    public Iterator<T> iterator() {
        return this._objValList.iterator();
    }

    @Override
    public Object[] toArray() {
        return this._objValList.toArray();
    }

    @Override
    public<A> A[] toArray(A[] a) {
        return this._objValList.toArray(a);
    }

    @Override
    public boolean add(T e) {
        if (e != null) {
            return this._objValList.add(e);
        } else {
            return false;
        }
    }

    @Override
    public boolean remove(Object e) {
        if (e != null) {
            return this._objValList.remove(e);
        } else {
            return false;
        }
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        if (c != null) {
            return this._objValList.contains(c);
        } else {
            return false;
        }
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        if (c != null) {
            return this._objValList.addAll(c);
        } else {
            return false;
        }
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        if (c != null) {
            return this._objValList.addAll(index, c);
        } else {
            return false;
        }
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        if (c != null) {
            return this._objValList.remove(c);
        } else {
            return false;
        }
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        if (c != null) {
            return this._objValList.retainAll(c);
        } else {
            return false;
        }
    }

    @Override
    public void clear() {
        this._objValList.clear();
    }

    @Override
    public T get(int index) {
        return this._objValList.get(index);
    }

    @Override
    public T set(int index, T element) {
        if (element != null) {
            return this._objValList.set(index, element);
        } else {
            return null;
        }
    }

    @Override
    public void add(int index, T element) {
        if (element != null) {
            this._objValList.add(index, element);
        }
    }

    @Override
    public T remove(int index) {
        return this._objValList.remove(index);
    }

    @Override
    public int indexOf(Object o) {
        if (o != null) {
            return this._objValList.indexOf(o);
        } else {
            return -1;
        }
    }

    @Override
    public int lastIndexOf(Object o) {
        if (o != null) {
            return this._objValList.lastIndexOf(o);
        } else {
            return -1;
        }
    }

    @Override
    public ListIterator<T> listIterator() {
        return this._objValList.listIterator();
    }

    @Override
    public ListIterator<T> listIterator(
        int index) {
        return this._objValList.listIterator(index);
    }

    @Override
    public List<T> subList(
        int fromIndex,
        int toIndex) {
        return this._objValList.subList(
            fromIndex, toIndex
        );
    }
}
