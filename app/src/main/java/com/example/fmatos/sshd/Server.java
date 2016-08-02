package com.example.fmatos.sshd;

import org.apache.sshd.SshServer;
import org.apache.sshd.common.NamedFactory;
import org.apache.sshd.server.Command;
import org.apache.sshd.server.PasswordAuthenticator;
import org.apache.sshd.server.command.ScpCommandFactory;
import org.apache.sshd.server.keyprovider.SimpleGeneratorHostKeyProvider;
import org.apache.sshd.server.session.ServerSession;
import org.apache.sshd.server.sftp.SftpSubsystem;

import java.io.IOException;
import java.util.Arrays;

/**
 * Created by fabio.goncalves on 2/08/2016.
 */

public class Server {


    public void doAll() {

        startSSHServer();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void startSSHServer() {
        int port = 8888;
        SshServer sshd = SshServer.setUpDefaultServer();
        sshd.setPort(port);
        sshd.setKeyPairProvider(new SimpleGeneratorHostKeyProvider(
                "src/test/resources/hostkey.ser"));
        sshd.setSubsystemFactories(Arrays
                .<NamedFactory<Command>>asList(new SftpSubsystem.Factory()));
        sshd.setCommandFactory(new ScpCommandFactory());
        sshd.setPasswordAuthenticator(new PasswordAuthenticator() {

            @Override
            public boolean authenticate(String u, String p, ServerSession s) {
                return ("sftptest".equals(u) && "sftptest".equals(p));
            }
        });

        try {
            sshd.start();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}