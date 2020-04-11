package org.longj.gun.commons.utils;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;

import java.io.IOException;
import java.util.Calendar;

/**
 * ftp操作
 *
 * @author LongJ
 * @date  2020/04/09 09:04
 */
public class FtpUtil {



    public static FTPClient getConnect(FtpConnectInfo ftpConnectInfo){

        FTPClient client = new FTPClient();
        try {
            client.connect(ftpConnectInfo.getHostIp(), Integer.parseInt( ftpConnectInfo.getHostPort() ));
            client.login(ftpConnectInfo.getUserName(), ftpConnectInfo.getUserPassword());
//            client.setFileType( FTPClient.BINARY_FILE_TYPE);

            if( ftpConnectInfo.getConnectTypeIsActive() ) {
                // 主动模式
                client.enterLocalActiveMode();
            } else {
                //被动模式
                System.out.println("被动模式");
                client.enterLocalPassiveMode();
            }


            int reply = client.getReplyCode();
            System.out.println("reply: "+reply);
            //System.out.println("reply:"+reply);
            if (!FTPReply.isPositiveCompletion(reply)) {
                closeFtp(client);
                throw new RuntimeException("ftp连接失败");
            }
            System.out.println("FTP服务器连接成功");
        } catch (Exception e) {
            throw new RuntimeException("FTP登录失败: "+e.getMessage());
        }
        return client;
    }

    public static void closeFtp(FTPClient client) {
        if (client != null && client.isConnected()) {
            try {
                client.logout();
            } catch ( IOException e ) {
                e.printStackTrace();
            }

            try {
                client.disconnect();
            } catch ( IOException e ) {
                e.printStackTrace();
            }
        }
    }

    public static void main( String[] args ) throws IOException {
        FtpConnectInfo connectInfo = new FtpConnectInfo();
        connectInfo.setHostIp( "192.168.1.11" );
        connectInfo.setHostPort( "21" );
        connectInfo.setUserName( "gftp" );
        connectInfo.setUserPassword( "90iopklM," );
        connectInfo.setConnectTypeIsActive( false );
        FTPClient client = FtpUtil.getConnect( connectInfo );
        System.out.println( client.isAvailable() );
        System.out.println( client.isConnected() );
        System.out.println( client.printWorkingDirectory() );
        client.changeWorkingDirectory( "/home/gftp" );
        System.out.println( client.getDataConnectionMode() );
        System.out.println( client.makeDirectory( "hello" ) );
        FTPFile[] ftpFiles = client.listFiles();
        System.out.println("ftpFiles: "+ftpFiles.length);
        System.out.println( client.getStatus() );
//        client.changeWorkingDirectory( "/app" );
//        ftpFiles = client.listFiles();
//        System.out.println("ftpFiles: "+ftpFiles.length);
//        System.out.println( client.printWorkingDirectory() );

        for (FTPFile file : ftpFiles) {
            System.out.println(file.getTimestamp());
            System.out.println( file.getTimestamp().getTimeInMillis() );
            System.out.println( file.getTimestamp().get( Calendar.ZONE_OFFSET ) );
            System.out.println(TimeUtil.timestampOfMilli2String( file.getTimestamp().getTimeInMillis()+ file.getTimestamp().get( Calendar.ZONE_OFFSET  ) ));
        }
    }

    public static class FtpConnectInfo{
        private String hostIp;
        private String hostPort;
        private String userName;
        private String userPassword;
        private boolean connectTypeIsActive = false;

        public String getHostIp() {
            return hostIp;
        }

        public void setHostIp( String hostIp ) {
            this.hostIp = hostIp;
        }

        public String getHostPort() {
            return hostPort;
        }

        public void setHostPort( String hostPort ) {
            this.hostPort = hostPort;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName( String userName ) {
            this.userName = userName;
        }

        public String getUserPassword() {
            return userPassword;
        }

        public void setUserPassword( String userPassword ) {
            this.userPassword = userPassword;
        }

        public boolean getConnectTypeIsActive() {
            return connectTypeIsActive;
        }

        public void setConnectTypeIsActive( boolean connectTypeIsActive ) {
            this.connectTypeIsActive = connectTypeIsActive;
        }
    }
}
