package org.longj.gun.commons.utils;

import com.jcraft.jsch.*;

import java.util.Properties;
import java.util.Vector;

/**
 * sftp操作
 *
 * @author LongJ
 * @date 2020/04/09 09:28
 */
public class SftpUtil {

    public static ChannelSftp getConnect( SftpConnectInfo connectInfo){
        ChannelSftp sftp ;
        try {
            JSch jsch = new JSch();
            Session sshSession = jsch.getSession(
                    connectInfo.getUserName(),
                    connectInfo.getHostIp(),
                    Integer.parseInt( connectInfo.getHostPort()) );
            sshSession.setPassword( connectInfo.getUserPassword() );
            Properties sshConfig = new Properties();
            sshConfig.put("StrictHostKeyChecking", "no");
            sshSession.setConfig(sshConfig);
            sshSession.connect();
            Channel channel = sshSession.openChannel("sftp");
            channel.connect();
            sftp = (ChannelSftp) channel;
            //log.info("SFTP connect to " + host +" successed");
            //System.out.println("Connected to " + host + ".");
            System.out.println("SFTP服务器连接成功");
        } catch (Exception e) {
            //log.info("SFTP connect to " + host +" failed:"+e);
            //System.out.println("Connect to " + host + " failed.");
            throw new RuntimeException("SFTP connect to " + connectInfo.getHostIp() +" failed:"+e);
        }
        return sftp;
    }

    public static  void disconnect(ChannelSftp sftp) {
        if(sftp != null){
            if(sftp.isConnected()){
                sftp.disconnect();
            }else if(sftp.isClosed()){
                sftp.exit();
            }
            try {
                if (null != sftp.getSession()) {
                    sftp.getSession().disconnect();
                }
            } catch ( JSchException e) {
                System.out.println("关闭连接");
                throw new RuntimeException("Close sftp session JSchException:"+e);
            }
        }
    }

    public static void main( String[] args ) throws SftpException {

        SftpUtil.SftpConnectInfo connectInfo = new SftpUtil.SftpConnectInfo();
        connectInfo.setHostIp( "47.106.213.24" );
        connectInfo.setHostPort( "22" );
        connectInfo.setUserName( "py" );
        connectInfo.setUserPassword( "py" );

        ChannelSftp connect = SftpUtil.getConnect( connectInfo );

        Vector<ChannelSftp.LsEntry> sftpFile = connect.ls( "/home/py" );
        for ( ChannelSftp.LsEntry entry : sftpFile ){
            System.out.println( entry.getFilename() + " >>> " + entry.getAttrs().getMtimeString() );
            System.out.println( entry.getFilename() + " >>> " + entry.getAttrs().getMTime() );
        }


        SftpUtil.disconnect(connect);


    }

    public static class SftpConnectInfo{
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

        public boolean isConnectTypeIsActive() {
            return connectTypeIsActive;
        }

        public void setConnectTypeIsActive( boolean connectTypeIsActive ) {
            this.connectTypeIsActive = connectTypeIsActive;
        }
    }
}
