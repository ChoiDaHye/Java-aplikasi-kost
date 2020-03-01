package interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface interfaces extends Remote {
    
    public List<penghuni> getphn(int d1) throws RemoteException;
    public String tambah_phn(String d1, String d2, String d3, String d4, String d5) throws RemoteException;
    public String tambah_tgn(int d2, String d3, int d4, int d5, String d6, int d7) throws RemoteException;
    public List<bayar> getbyr(int d, String g, int f) throws RemoteException;
    public List<penghuni> getphn2() throws RemoteException;
    public List<kamar> getkmr1() throws RemoteException;
    public List<lantai> getkmr2() throws RemoteException;
    public List<kamar> getkmr3(int c, int d) throws RemoteException;
    public List<bulan> getbln1(int d) throws RemoteException;
    public List<bulan> getbln2() throws RemoteException;
    public String pindah_phn(int awal, int baru) throws RemoteException;
    public String akhiri(int id) throws RemoteException;
}