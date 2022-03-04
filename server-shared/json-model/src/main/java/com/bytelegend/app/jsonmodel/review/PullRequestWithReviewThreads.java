/*
 * Copyright 2021 ByteLegend Technologies and the original author or authors.
 *
 * Licensed under the GNU Affero General Public License v3.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://github.com/ByteLegend/ByteLegend/blob/master/LICENSE
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.bytelegend.app.jsonmodel.review;

import java.util.List;

public class PullRequestWithReviewThreads {

    /**
     * data : {"repository":{"pullRequest":{"id":"MDExOlB1bGxSZXF1ZXN0NDE0NzA2MDY3","databaseId":414706067,"headRepository":{"nameWithOwner":"hcsp/implement-sem-version"},"reviewThreads":{"nodes":[{"comments":{"nodes":[{"body":"太简单粗暴了，假如有4个版本号呢？要多少层if/else？请参考一下其他同学的最佳答案。","author":{"login":"hcsp-bot"},"commit":{"oid":"abfc7ed0193611057a68d9b1a03362801473c27a"},"path":"src/main/java/com/github/hcsp/maven/Version.java"},{"body":"收到。","author":{"login":"haomega"},"commit":{"oid":"abfc7ed0193611057a68d9b1a03362801473c27a"},"path":"src/main/java/com/github/hcsp/maven/Version.java"}]},"line":17,"originalLine":17,"originalStartLine":null,"startLine":null,"startDiffSide":null},{"comments":{"nodes":[{"body":"test","author":{"login":"blindpirate"},"commit":{"oid":"abfc7ed0193611057a68d9b1a03362801473c27a"},"path":"src/main/java/com/github/hcsp/maven/Version.java"}]},"line":27,"originalLine":27,"originalStartLine":null,"startLine":null,"startDiffSide":null},{"comments":{"nodes":[{"body":"test","author":{"login":"blindpirate"},"commit":{"oid":"abfc7ed0193611057a68d9b1a03362801473c27a"},"path":"src/main/java/com/github/hcsp/maven/Version.java"}]},"line":21,"originalLine":21,"originalStartLine":null,"startLine":null,"startDiffSide":null}]},"files":{"edges":[{"node":{"path":"src/main/java/com/github/hcsp/maven/Version.java"}}]}}}}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * repository : {"pullRequest":{"id":"MDExOlB1bGxSZXF1ZXN0NDE0NzA2MDY3","databaseId":414706067,"headRepository":{"nameWithOwner":"hcsp/implement-sem-version"},"reviewThreads":{"nodes":[{"comments":{"nodes":[{"body":"太简单粗暴了，假如有4个版本号呢？要多少层if/else？请参考一下其他同学的最佳答案。","author":{"login":"hcsp-bot"},"commit":{"oid":"abfc7ed0193611057a68d9b1a03362801473c27a"},"path":"src/main/java/com/github/hcsp/maven/Version.java"},{"body":"收到。","author":{"login":"haomega"},"commit":{"oid":"abfc7ed0193611057a68d9b1a03362801473c27a"},"path":"src/main/java/com/github/hcsp/maven/Version.java"}]},"line":17,"originalLine":17,"originalStartLine":null,"startLine":null,"startDiffSide":null},{"comments":{"nodes":[{"body":"test","author":{"login":"blindpirate"},"commit":{"oid":"abfc7ed0193611057a68d9b1a03362801473c27a"},"path":"src/main/java/com/github/hcsp/maven/Version.java"}]},"line":27,"originalLine":27,"originalStartLine":null,"startLine":null,"startDiffSide":null},{"comments":{"nodes":[{"body":"test","author":{"login":"blindpirate"},"commit":{"oid":"abfc7ed0193611057a68d9b1a03362801473c27a"},"path":"src/main/java/com/github/hcsp/maven/Version.java"}]},"line":21,"originalLine":21,"originalStartLine":null,"startLine":null,"startDiffSide":null}]},"files":{"edges":[{"node":{"path":"src/main/java/com/github/hcsp/maven/Version.java"}}]}}}
         */

        private RepositoryBean repository;

        public RepositoryBean getRepository() {
            return repository;
        }

        public void setRepository(RepositoryBean repository) {
            this.repository = repository;
        }

        public static class RepositoryBean {
            /**
             * pullRequest : {"id":"MDExOlB1bGxSZXF1ZXN0NDE0NzA2MDY3","databaseId":414706067,"headRepository":{"nameWithOwner":"hcsp/implement-sem-version"},"reviewThreads":{"nodes":[{"comments":{"nodes":[{"body":"太简单粗暴了，假如有4个版本号呢？要多少层if/else？请参考一下其他同学的最佳答案。","author":{"login":"hcsp-bot"},"commit":{"oid":"abfc7ed0193611057a68d9b1a03362801473c27a"},"path":"src/main/java/com/github/hcsp/maven/Version.java"},{"body":"收到。","author":{"login":"haomega"},"commit":{"oid":"abfc7ed0193611057a68d9b1a03362801473c27a"},"path":"src/main/java/com/github/hcsp/maven/Version.java"}]},"line":17,"originalLine":17,"originalStartLine":null,"startLine":null,"startDiffSide":null},{"comments":{"nodes":[{"body":"test","author":{"login":"blindpirate"},"commit":{"oid":"abfc7ed0193611057a68d9b1a03362801473c27a"},"path":"src/main/java/com/github/hcsp/maven/Version.java"}]},"line":27,"originalLine":27,"originalStartLine":null,"startLine":null,"startDiffSide":null},{"comments":{"nodes":[{"body":"test","author":{"login":"blindpirate"},"commit":{"oid":"abfc7ed0193611057a68d9b1a03362801473c27a"},"path":"src/main/java/com/github/hcsp/maven/Version.java"}]},"line":21,"originalLine":21,"originalStartLine":null,"startLine":null,"startDiffSide":null}]},"files":{"edges":[{"node":{"path":"src/main/java/com/github/hcsp/maven/Version.java"}}]}}
             */

            private PullRequestBean pullRequest;

            public PullRequestBean getPullRequest() {
                return pullRequest;
            }

            public void setPullRequest(PullRequestBean pullRequest) {
                this.pullRequest = pullRequest;
            }

            public static class PullRequestBean {
                /**
                 * id : MDExOlB1bGxSZXF1ZXN0NDE0NzA2MDY3
                 * databaseId : 414706067
                 * headRepository : {"nameWithOwner":"hcsp/implement-sem-version"}
                 * reviewThreads : {"nodes":[{"comments":{"nodes":[{"body":"太简单粗暴了，假如有4个版本号呢？要多少层if/else？请参考一下其他同学的最佳答案。","author":{"login":"hcsp-bot"},"commit":{"oid":"abfc7ed0193611057a68d9b1a03362801473c27a"},"path":"src/main/java/com/github/hcsp/maven/Version.java"},{"body":"收到。","author":{"login":"haomega"},"commit":{"oid":"abfc7ed0193611057a68d9b1a03362801473c27a"},"path":"src/main/java/com/github/hcsp/maven/Version.java"}]},"line":17,"originalLine":17,"originalStartLine":null,"startLine":null,"startDiffSide":null},{"comments":{"nodes":[{"body":"test","author":{"login":"blindpirate"},"commit":{"oid":"abfc7ed0193611057a68d9b1a03362801473c27a"},"path":"src/main/java/com/github/hcsp/maven/Version.java"}]},"line":27,"originalLine":27,"originalStartLine":null,"startLine":null,"startDiffSide":null},{"comments":{"nodes":[{"body":"test","author":{"login":"blindpirate"},"commit":{"oid":"abfc7ed0193611057a68d9b1a03362801473c27a"},"path":"src/main/java/com/github/hcsp/maven/Version.java"}]},"line":21,"originalLine":21,"originalStartLine":null,"startLine":null,"startDiffSide":null}]}
                 * files : {"edges":[{"node":{"path":"src/main/java/com/github/hcsp/maven/Version.java"}}]}
                 */

                private String id;
                private int databaseId;
                private HeadRepositoryBean headRepository;
                private ReviewThreadsBean reviewThreads;
                private FilesBean files;

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }

                public int getDatabaseId() {
                    return databaseId;
                }

                public void setDatabaseId(int databaseId) {
                    this.databaseId = databaseId;
                }

                public HeadRepositoryBean getHeadRepository() {
                    return headRepository;
                }

                public void setHeadRepository(HeadRepositoryBean headRepository) {
                    this.headRepository = headRepository;
                }

                public ReviewThreadsBean getReviewThreads() {
                    return reviewThreads;
                }

                public void setReviewThreads(ReviewThreadsBean reviewThreads) {
                    this.reviewThreads = reviewThreads;
                }

                public FilesBean getFiles() {
                    return files;
                }

                public void setFiles(FilesBean files) {
                    this.files = files;
                }

                public static class HeadRepositoryBean {
                    /**
                     * nameWithOwner : hcsp/implement-sem-version
                     */

                    private String nameWithOwner;

                    public String getNameWithOwner() {
                        return nameWithOwner;
                    }

                    public void setNameWithOwner(String nameWithOwner) {
                        this.nameWithOwner = nameWithOwner;
                    }
                }

                public static class ReviewThreadsBean {
                    private List<NodesBeanX> nodes;

                    public List<NodesBeanX> getNodes() {
                        return nodes;
                    }

                    public void setNodes(List<NodesBeanX> nodes) {
                        this.nodes = nodes;
                    }

                    public static class NodesBeanX {
                        /**
                         * comments : {"nodes":[{"body":"太简单粗暴了，假如有4个版本号呢？要多少层if/else？请参考一下其他同学的最佳答案。","author":{"login":"hcsp-bot"},"commit":{"oid":"abfc7ed0193611057a68d9b1a03362801473c27a"},"path":"src/main/java/com/github/hcsp/maven/Version.java"},{"body":"收到。","author":{"login":"haomega"},"commit":{"oid":"abfc7ed0193611057a68d9b1a03362801473c27a"},"path":"src/main/java/com/github/hcsp/maven/Version.java"}]}
                         * line : 17
                         * originalLine : 17
                         * originalStartLine : null
                         * startLine : null
                         * startDiffSide : null
                         */

                        private CommentsBean comments;
                        private int line;
                        private int originalLine;
                        private Object originalStartLine;
                        private Object startLine;
                        private Object startDiffSide;

                        public CommentsBean getComments() {
                            return comments;
                        }

                        public void setComments(CommentsBean comments) {
                            this.comments = comments;
                        }

                        public int getLine() {
                            return line;
                        }

                        public void setLine(int line) {
                            this.line = line;
                        }

                        public int getOriginalLine() {
                            return originalLine;
                        }

                        public void setOriginalLine(int originalLine) {
                            this.originalLine = originalLine;
                        }

                        public Object getOriginalStartLine() {
                            return originalStartLine;
                        }

                        public void setOriginalStartLine(Object originalStartLine) {
                            this.originalStartLine = originalStartLine;
                        }

                        public Object getStartLine() {
                            return startLine;
                        }

                        public void setStartLine(Object startLine) {
                            this.startLine = startLine;
                        }

                        public Object getStartDiffSide() {
                            return startDiffSide;
                        }

                        public void setStartDiffSide(Object startDiffSide) {
                            this.startDiffSide = startDiffSide;
                        }

                        public static class CommentsBean {
                            private List<NodesBean> nodes;

                            public List<NodesBean> getNodes() {
                                return nodes;
                            }

                            public void setNodes(List<NodesBean> nodes) {
                                this.nodes = nodes;
                            }

                            public static class NodesBean {
                                /**
                                 * body : 太简单粗暴了，假如有4个版本号呢？要多少层if/else？请参考一下其他同学的最佳答案。
                                 * author : {"login":"hcsp-bot"}
                                 * commit : {"oid":"abfc7ed0193611057a68d9b1a03362801473c27a"}
                                 * path : src/main/java/com/github/hcsp/maven/Version.java
                                 */

                                private String body;
                                private AuthorBean author;
                                private CommitBean commit;
                                private String path;

                                public String getBody() {
                                    return body;
                                }

                                public void setBody(String body) {
                                    this.body = body;
                                }

                                public AuthorBean getAuthor() {
                                    return author;
                                }

                                public void setAuthor(AuthorBean author) {
                                    this.author = author;
                                }

                                public CommitBean getCommit() {
                                    return commit;
                                }

                                public void setCommit(CommitBean commit) {
                                    this.commit = commit;
                                }

                                public String getPath() {
                                    return path;
                                }

                                public void setPath(String path) {
                                    this.path = path;
                                }

                                public static class AuthorBean {
                                    /**
                                     * login : hcsp-bot
                                     */

                                    private String login;

                                    public String getLogin() {
                                        return login;
                                    }

                                    public void setLogin(String login) {
                                        this.login = login;
                                    }
                                }

                                public static class CommitBean {
                                    /**
                                     * oid : abfc7ed0193611057a68d9b1a03362801473c27a
                                     */

                                    private String oid;

                                    public String getOid() {
                                        return oid;
                                    }

                                    public void setOid(String oid) {
                                        this.oid = oid;
                                    }
                                }
                            }
                        }
                    }
                }

                public static class FilesBean {
                    private List<EdgesBean> edges;

                    public List<EdgesBean> getEdges() {
                        return edges;
                    }

                    public void setEdges(List<EdgesBean> edges) {
                        this.edges = edges;
                    }

                    public static class EdgesBean {
                        /**
                         * node : {"path":"src/main/java/com/github/hcsp/maven/Version.java"}
                         */

                        private NodeBean node;

                        public NodeBean getNode() {
                            return node;
                        }

                        public void setNode(NodeBean node) {
                            this.node = node;
                        }

                        public static class NodeBean {
                            /**
                             * path : src/main/java/com/github/hcsp/maven/Version.java
                             */

                            private String path;

                            public String getPath() {
                                return path;
                            }

                            public void setPath(String path) {
                                this.path = path;
                            }
                        }
                    }
                }
            }
        }
    }
}
